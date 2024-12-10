import java.util.Arrays;
import java.util.List;

@Listener
public void onStart(GameStartedServerEvent event)
{
    LanguageHandler.load();
    ConfigHandler.load();
    DataHandler.load();
    
    Sponge.getServiceManager()
            .getRegistration(EconomyService.class)
            .ifPresent(prov -> economyService = prov.getProvider());

    // 명령어 등록
    NationCmds.create(this);

    // 리스너 리스트
    List<Object> listeners = Arrays.asList(
        new BuildPermListener(),
        new ChatListener(),
        new ExplosionListener(),
        new FireListener(),
        new InteractPermListener(),
        new MobSpawnListener(),
        new MobSpawningListener(),
        new PlayerConnectionListener(),
        new PlayerMoveListener(),
        new PvPListener()
    );

    // 리스너 등록
    listeners.forEach(listener -> Sponge.getEventManager().registerListeners(this, listener));

    // 세금 수집 작업 설정
    LocalDateTime localNow = LocalDateTime.now();
    ZonedDateTime zonedNow = ZonedDateTime.of(localNow, ZoneId.systemDefault());
    ZonedDateTime zonedNext = zonedNow.withHour(12).withMinute(0).withSecond(0);
    if (zonedNow.compareTo(zonedNext) > 0)
        zonedNext = zonedNext.plusDays(1);
    long initialDelay = Duration.between(zonedNow, zonedNext).getSeconds();

    Sponge.getScheduler()
            .createTaskBuilder()
            .execute(new TaxesCollectRunnable())
            .delay(initialDelay, TimeUnit.SECONDS)
            .interval(1, TimeUnit.DAYS)
            .async()
            .submit(this);

    logger.info("Plugin ready");
}

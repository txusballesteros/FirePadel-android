package firebasebarcelona.firepadel.domain.cases;

import firebasebarcelona.firepadel.data.player.repository.PlayerRepository;
import firebasebarcelona.firepadel.domain.models.Player;
import javax.inject.Inject;

public class GetLocalPlayerUseCase extends AbstractUseCase {
  private final PlayerRepository playerRepository;
  private Callback callback;

  @Inject
  public GetLocalPlayerUseCase(PlayerRepository playerRepository) {
    this.playerRepository = playerRepository;
  }

  @Override
  protected void onRun() {
    Player player = playerRepository.getMyPlayer();
    if (player != null) {
      callback.onGetLocalPlayerSuccess(player);
    } else {
      callback.onGetLocalPlayerError();
    }
  }

  public void execute(Callback callback) {
    this.callback = callback;
    run();
  }

  public interface Callback {
    void onGetLocalPlayerSuccess(Player player);
    void onGetLocalPlayerError();
  }
}

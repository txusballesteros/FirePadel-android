package firebasebarcelona.wallapadel.domain.cases;

import android.app.Application;
import firebasebarcelona.wallapadel.app.PadelApplication;
import firebasebarcelona.wallapadel.data.courts.repository.CourtRepository;
import firebasebarcelona.wallapadel.domain.models.Court;
import firebasebarcelona.wallapadel.domain.models.Player;
import javax.inject.Inject;

public class AddPlayerToCourtUseCase extends AbstractUseCase {
  private final CourtRepository courtRepository;
  private String courtId;
  private Player player;
  private Callback callback;

  @Inject
  public AddPlayerToCourtUseCase(CourtRepository courtRepository) {
    this.courtRepository = courtRepository;
  }

  public void execute(String courtId, Player player, Callback callback) {
    this.courtId = courtId;
    this.player = player;
    this.callback = callback;
    run();
  }

  @Override
  public void run() {
    Court court = courtRepository.addPlayerToCourt(courtId, player);
    if (court == null) {
      callback.onAddPlayerToCourtError();
    } else {
      callback.onAddPlayerToCourtSuccess(court);
    }
  }

  public interface Callback {
    void onAddPlayerToCourtSuccess(Court court);
    void onAddPlayerToCourtError();
  }
}

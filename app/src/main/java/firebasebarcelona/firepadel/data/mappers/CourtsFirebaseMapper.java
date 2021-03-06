package firebasebarcelona.firepadel.data.mappers;

import com.google.firebase.database.DataSnapshot;
import firebasebarcelona.firepadel.data.models.CourtData;
import firebasebarcelona.firepadel.data.models.PlayerData;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton public class CourtsFirebaseMapper {

  @Inject
  public CourtsFirebaseMapper() {
  }

  public List<CourtData> map(DataSnapshot snapshot) {
    List<CourtData> courts = new ArrayList<>();
    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
      CourtData courtData = new CourtData();
      courtData.setId(String.valueOf(dataSnapshot.child("id").getValue()));
      setPlayers(dataSnapshot, courtData);
      courts.add(courtData);
    }
    return courts;
  }

  private void setPlayers(DataSnapshot dataSnapshot, CourtData courtData) {
    List<PlayerData> players = new ArrayList<>();
    for (DataSnapshot playersSnapshot : dataSnapshot.child("users").getChildren()) {
      PlayerData playerData = playersSnapshot.getValue(PlayerData.class);
      players.add(playerData);
    }
    courtData.setPlayers(players);
  }

  public CourtData mapCourt(DataSnapshot snapshot) {
    CourtData courtData = new CourtData();
    courtData.setId(String.valueOf(snapshot.child("id").getValue()));
    setPlayers(snapshot, courtData);
    return courtData;
  }

  public String getPlayerId(DataSnapshot snapshot) {
    return (String) snapshot.child("email").getValue();
  }

  public String getCourtId(String courtId) {
    return "court"+courtId;
  }
}

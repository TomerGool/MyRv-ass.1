package com.example.myrvjava;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import android.widget.Toast;
import androidx.appcompat.widget.SearchView;


public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CharacterAdapter characterAdapter;
    private List<CharacterModel> characterList;
    private List<CharacterModel> filteredList; // new list to hold filtered characters
    private SearchView searchView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        recyclerView = findViewById(R.id.recyclerView);
        searchView = findViewById(R.id.search_View);
        searchView.clearFocus();
        characterList = new ArrayList<>();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText);
                return true;
            }
        });



        // Initialize character list with "The Vikings" cast
        characterList = new ArrayList<>();
        filteredList = new ArrayList<>();

        characterList.add(new CharacterModel(R.drawable.katheryn_winnick, "Katheryn Winnick", "Lagertha - Known for her role as Lagertha, a shieldmaiden and former wife of Ragnar Lothbrok in the historical drama series Vikings. 71 episodes, 2013-2020"));
        characterList.add(new CharacterModel(R.drawable.gustaf_skarsgard, "Gustaf Skarsgård", "Floki - Portrayed the character Floki, an eccentric and inventive shipbuilder in Vikings, known for his loyalty to Ragnar. 68 episodes, 2013-2020"));
        characterList.add(new CharacterModel(R.drawable.alexander_ludwig, "Alexander Ludwig", "Bjorn Lothbrok -Played Bjorn Ironside, the son of Ragnar Lothbrok and Lagertha, who grows into a formidable Viking warrior and leader. 67 episodes, 2014-2020"));
        characterList.add(new CharacterModel(R.drawable.georgia_hirst, "Georgia Hirst", "Torvi -Portrayed Torvi, initially introduced as a shieldmaiden and later becoming a key character in the series. 63 episodes, 2014-2020"));
        characterList.add(new CharacterModel(R.drawable.alex_hogh_andersen, "Alex Høgh Andersen", "Ivar -Known for his role as Ivar the Boneless, a cunning and ambitious Viking leader, and one of Ragnar's sons. 53 episodes, 2016-2020"));
        characterList.add(new CharacterModel(R.drawable.jordan_patrick_smith, "Jordan Patrick Smith", "Ubbe -Played Ubbe, another son of Ragnar Lothbrok who embarks on his own journey and becomes a prominent character in the series. 51 episodes, 2016-2020"));
        characterList.add(new CharacterModel(R.drawable.marco_ilso, "Marco Ilsø", "Hvitserk -Portrayed Hvitserk, another son of Ragnar Lothbrok, who faces his own challenges and transformations throughout the series. 49 episodes, 2016-2020"));
        characterList.add(new CharacterModel(R.drawable.peter_franzen, "Peter Franzén", "King Harald Finehair -Played King Harald Finehair, a powerful and ambitious ruler who seeks to become the King of all Norway. 49 episodes, 2016-2020"));
        characterList.add(new CharacterModel(R.drawable.travis_fimmel, "Travis Fimmel", "Ragnar Lothbrok -Initially starred as Ragnar Lothbrok, a legendary Norse hero and farmer who rises to become a legendary Viking king and warrior. 45 episodes, 2013-2017"));
        characterList.add(new CharacterModel(R.drawable.clive_standen, "Clive Standen", "Rollo -Known for his role as Rollo, Ragnar Lothbrok's brother, who initially struggles with loyalty but later plays a significant role in the Viking world. 45 episodes, 2013-2018"));

        // Initialize RecyclerView and set its adapter
        characterAdapter = new CharacterAdapter(characterList,this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(characterAdapter);
    }

    private void filterList(String text) {
        List<CharacterModel> filteredList = new ArrayList<CharacterModel>();
        for(CharacterModel characterModel:characterList){
            if(characterModel.getName().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(characterModel);
            }
            if(filteredList.isEmpty()){
                Toast.makeText(this, "No Data Found", Toast.LENGTH_SHORT).show();
            }
            else{
                characterAdapter.setFilteredList((filteredList));
            }
        }

    }

    private void filter(String query) {
        filteredList = findViewById(R.id.search_View);

        filteredList.clear();
        for (CharacterModel character : characterList) {
            if (character.getName().toLowerCase().contains(query.toLowerCase())) {
                filteredList.add(character);
            }
        }
        characterAdapter.setFilter(filteredList);
    }

}

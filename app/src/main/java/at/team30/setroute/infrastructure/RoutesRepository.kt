package at.team30.setroute.infrastructure

import android.widget.Switch
import at.team30.setroute.R
import at.team30.setroute.models.Route
import com.google.android.gms.maps.model.LatLng


class RoutesRepository : IRoutesRepository {

    val description_coffee : String = "Short relaxing morning walk through Graz most famous coffee shops. The walk starts at Tribeka, " +
            "which is popular coffee place among students. Than it goes via Stadtpark to Kunsthauscafe, " +
            "where you can see Murinsel. The walk ends at the insider tip, Omas Teekanne."
    val description_coffee_de : String = "Kurzer entspannender morgentlicher Spaziergang durch die bekanntesten Grazer Cafés." +
            " Der Spaziergang startet beim Kaffeehaus Tribeka, welches " +
            "ein beliebter Treffpunkt für Studenten ist. Dann führt die Runde über den Stadtpark zum Kunsthauscafe, " +
            "wo man die Murinsel sehen kann. Der Spaziergang endet beim Geheimtipp, Omas Teekanne."
    val description_coffee_ru : String = "Короткая расслабляющая утренняя прогулка по самым известным кофейням Граца. Прогулка начинаеться в Tribeka, " +
            "что являеться популярным местом среди студентов. После этого маршрут идёт через Stadtpark в Kunsthaus, " +
            "где Вы сможете увидить Murinsel. Прогулка заканчиваеться в Omas Teekane."

    val description_coffee2 : String = "Long walk through Graz most famous coffee shops. " +
            "It starts in Zinsendorfgasse at the Beanery Coffeeshop and " +
            "continuous at Tribeka which is located in Leonhardstrasse. " +
            "After small break you will go to Black Coffee Breakfast Shop to have a snack. " +
            "At the end of the walk you will enjoy " +
            "something sweet at Konditorei Philipp"
    val description_coffee2_de : String = "Langer Spaziergang durch die berühmtesten Grazer Cafés. " +
            "Der Spaziergang startet in der Zinsendorfgasse beim Beanery Coffeeshop und geht beim tribeka " +
            "weiter, welcher in der Leonhardstrasse liegt. " +
            "Nach einer kleinen Pause werden Sie eienen kleinen Snack im Black Coffee Breakfast Shop einnehmen. " +
            "Am Ende der Runde werden Sie etwas Süßes beim " +
            "Konditorei Philipp genießen."
    val description_coffee2_ru : String = "Долгая прогулка по самым известным кофейням Граца. " +
            "Прогулка начинаеться на улице Zinsendorfgasse в Beanery Coffeeshop, и продолжаеться в Tribeka, которая расположена" +
            "на Leonhardstrasse. После небольшой паузы Вы направитесь в Black Coffee Breakfast Shop что бы перекусить. " +
            "В конце маршрута Вы можете насладиться чем-нибудь сладким в Konditorei Philipp"

    val description_parks : String = "Long walk through Graz most beautiful parks. " +
            "The walk starts at Schloss Eggenberg where you can observe amazing peafowls. " +
            "After a long walk to Augarten, you can relax on " +
            "the bank of Mur. The walk continuous at Stadtpark, where you could have a bottle of Beer." +
            "The route ends at Botanischer Garten, where you can enjoy beautiful flowers."
    val description_parks_de : String = "Langer Spaziergang durch die schönsten Grazer Parks. " +
            "Der Spaziergang startet beim Schloss Eggenberg, wo Sie die wunderbaren Pfauen beobachten können. " +
            "Nach einem langen Spaziergang können Sie am Ufer der Mur entspannen. " +
            "Der Weg führt Sie weiter in den Stadtpark, wo Sie eine Flasche Bier trinken können." +
            "Die Runde endet beim Botanischer Garten. Dort können Sie wunderschöne Blümen genießen."
    val description_parks_ru : String = "Длинная прогулка по красивейшим паркам Граца. Прогулка начинаеться в Schloss Eggenberg," +
            "где вы можете понаблюдать за прекрасными Павлинами." +
            "После долгой дороги к Augarten, вы сможете отдохнуть на берегу Мура." +
            "Маршрут продолжится в Stadtpark, где вы сможете выпить бутылочку пива." +
            "Прогулка заканчиваеться в Botanischer Garten, где есть возможность насладиться прекрасными цветами."

    val description_sightseeing : String = "Short walk on top of Schlossberg. " +
            "The walk starts with climbing the beautiful staircases of Schlossberg Steig." +
            "When reaching the top you have awesome view over the city of Graz. You will be able to " +
            "discover astonishing historical places on the top of Graz."
    val description_sightseeing_de : String = "Kurzer Spaziergang auf dem Schlossberg. Der Spaziergang startet " +
            "indem man die wunderschönen Stiegen des Schlossbergs Steigs erklimmt. " +
            "Wenn Sie die Spitze erreicht haben, haben Sie einen einmaligen Blick über Graz." +
            "Sie haben die Möglichkeit atemberaubende historische Plätze zusehen."
    val description_sightseeing_ru : String = "Короткая прогулка по горе Schlossberg." +
            "Прогулка начинаеться с прекрасных ступенек у подножья горы." +
            "Когда Вы доберетесь до верха, Вам откроеться прекарсный вид на Город." +
            "После чего у Вас будет возможность посетить захватывающие дух исторические места."

    val description_dog_walk : String = "A dog walk through Graz. The route contains 4 Parks, Where you can walk " +
            "with your dog, and take a deep breath of fresh air. All the parks have beautiful lawns, so your" +
            "dog will have fun."
    val description_dog_walk_de : String = "Ein spaziergang mit dem Hund durch Graz. Die Route beinhaltet 4 Parks, " +
            "wo Sie mit Ihrem Hund spazieren gehen können und die frische Luft genießen können. Alle Parks haben" +
            "schöne Rasen, sodass Ihr Hund viel Spaß haben wird."
    val description_dog_walk_ru : String = "Прогулка с собакой по Грацу. Маршрут проходит через 4 парка, " +
            "где Вы можете пройтись со своей собакой и подышать свежим воздухом." +
            " В каждом из парков есть прекрасные полянки, которым обрадуется ваш питомец."

    val description_alcoholics : String = "An alcohol tour through best pubs i Graz."
    val description_alcoholics_de : String = "Ein Spaziergang für Alkohol-Enthusiasten."
    val description_alcoholics_ru : String = "Избранные пивнушки Граца."

    val description_romantic_walk : String = "Romantic walk through Graz."
    val description_romantic_walk_de : String = "Ein romantischer Spaziergang."
    val description_romantic_walk_ru : String = "Романтическая прогулка по городу."

    val description_sport_freaks : String = "Intensive training."
    val description_sport_freaks_de : String = "Ein intensives Training."
    val description_sport_freaks_ru : String = "Интенсивный тренинг."

    private val routes : List<Route> = listOf(
        Route(1,
            "Morning Coffee", "Morgentlicher Kaffee", "Утренний Кофе",
            Route.RouteType.COFFEE_LOVERS, 40, 2.5,
            description_coffee, description_coffee_de, description_coffee_ru,
            listOf(
                LatLng(47.068291509777374,15.4505505), //47.068291509777374, 15.4505505 //TRibeka technikerstraße
                LatLng(47.07399403129049, 15.44420308910896), //Cafe Parkhouse
                LatLng(47.064081837512184, 15.44954141656039), //Kaffee Bar
                LatLng(47.06846198659862, 15.434199801525855))),//47.06846198659862, 15.434199801525855 omas teekanne),
        Route(2, "Coffee Shops", "Kaffeehäuser", "Кофейни",
            Route.RouteType.COFFEE_LOVERS, 60, 3.3,
            description_coffee2, description_coffee2_de, description_coffee2_ru,
                listOf(
                        LatLng(47.07554301075298, 15.448352202183335),  //Beanery Specialty Coffee Graz
                        LatLng(47.0756058194418, 15.458812362194163), //Tribeka Leonhardstrasse
                        LatLng(47.06654683860752, 15.450234935802149), //Black Coffee Breakfast
                        LatLng(47.067861539911945, 15.460603898912762))), //Konditorei Philipp
        Route(3, "Parks of Graz", "Parks von Graz", "Парки Граца",
            Route.RouteType.PARK_LOVERS, 100, 7.6,
            description_parks, description_parks_de, description_parks_ru,
                listOf(
                        LatLng(47.072484081116535, 15.39473288008517), //Shlosspark Eggenberg
                        LatLng(47.06154982999469, 15.4348640486123), //Augarten
                        LatLng(47.07460820628103, 15.44435814485022), //Stadtpark
                        LatLng(47.081945418598984, 15.457179665111903))), //Botanischer Garten
        Route(4, "Sights", "Sehenswürdigkeiten", "Достопримечательности",
            Route.RouteType.SIGHTSEEING_ADDICTED, 12, 0.7,
            description_sightseeing, description_sightseeing_de, description_sightseeing_ru,
                listOf(
                        LatLng(47.07320439512898, 15.436876540124148), //Schlossbergrutsche
                        LatLng(47.07357690718118, 15.437702609500215), //UhrTurm
                        LatLng(47.0744342848516, 15.437047017456983), //Chinese Pavilion
                        LatLng(47.076018604665585, 15.437211390779604),//Glockenturm
                        LatLng(47.07642848990562, 15.43760142353206))), //kasematten
        Route(5, "Dog walk", "Hunde-Runde", "Прогулка с собакой",
            Route.RouteType.DOG_WALK, 30, 2.0,
            description_dog_walk, description_dog_walk_de, description_dog_walk_ru,
                listOf(
                        LatLng(47.072484081116535, 15.39473288008517), //Shlosspark Eggenberg
                        LatLng(47.06154982999469, 15.4348640486123), //Augarten
                        LatLng(47.07460820628103, 15.44435814485022), //Stadtpark
                        LatLng(47.081945418598984, 15.457179665111903))), //Botanischer Garten
        Route(6, "Alcoholics", "Beisl-Tour", "Алкотур",
            Route.RouteType.ALCOHOLICS, 60, 3.0,
            description_alcoholics, description_alcoholics_de, description_alcoholics_ru),
        Route(7, "Romantic walk", "Romantischer Spaziergang", "Романтическая прогулка",
            Route.RouteType.ROMANTIC_WALK, 35, 1.5,
            description_romantic_walk, description_romantic_walk_de, description_romantic_walk_ru),
        Route(8, "Sport freaks", "Sport Freak", "Спортсмен",
            Route.RouteType.SPORT_FREAKS, 42, 1.5,
            description_sport_freaks, description_sport_freaks_de, description_sport_freaks_ru)
    )



    override fun getRoutes(): List<Route> {
        return routes
    }

    override fun getRoutesById(id: Int): Route? {
        return try {
            routes.first { it.id == id }
        } catch (ex: NoSuchElementException) {
            null
        }
    }
}
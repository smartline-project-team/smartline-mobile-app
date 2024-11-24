import SwiftUI
import shared

struct ContentView: View {
    let viewModel = PlaceTypeViewModel()

    var body: some View {
        let placeTypes = viewModel.getPlaceTypes()

        VStack {
            ForEach(placeTypes, id: \.self) { placeType in
                Text(placeType.displayName)
                    .padding()
                    .onTapGesture {
                        // Обработка выбора
                    }
            }
        }
        .padding()
    }
}

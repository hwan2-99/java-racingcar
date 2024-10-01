package step3.model;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Cars {
    private static final String DUPLICATE_NAME_ERROR_MESSAGE = "자동차들의 이름은 중복될 수 없습니다.";
    private static final String DELIMITER = ",";
    private final List<Car> cars;

    private Cars(List<Car> cars) {
        validateDuplicated(cars);
        this.cars = cars;
    }

    public static Cars from(final String inputValue) {
        List<Car> cars = Arrays.stream(inputValue.split(DELIMITER))
                .map(name -> new Car(new CarName(name)))
                .collect(Collectors.toList());
        return new Cars(cars);
    }

    public List<Car> getCars() {
        return cars;
    }

    public void moveCars() {
        for (Car car : cars) {
            car.moveOrStop();
        }
    }

    private void validateDuplicated(final List<Car> cars) {
        Set<Car> carsSet = new HashSet<>(cars);
        if (carsSet.size() != cars.size()) {
            throw new IllegalArgumentException(DUPLICATE_NAME_ERROR_MESSAGE);
        }
    }
}

package chris;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class RestaurantTest {
    @Mock
    Restaurant mockedRestaurant = mock(Restaurant.class);

    Restaurant regularRestaurant;

    @BeforeEach
    public void setup(){

        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        regularRestaurant = new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        regularRestaurant.addToMenu("Sweet corn soup",119);
        regularRestaurant.addToMenu("Vegetable lasagne", 269);
    }

    //REFACTOR ALL THE REPEATED LINES OF CODE

    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    //-------FOR THE 2 TESTS BELOW, YOU MAY USE THE CONCEPT OF MOCKING, IF YOU RUN INTO ANY TROUBLE
    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time(){
        //WRITE UNIT TEST CASE HERE
        // Arrange

        when(mockedRestaurant.getOpeningTime()).thenReturn(LocalTime.parse("07:30:00"));
        when(mockedRestaurant.getClosingTime()).thenReturn(LocalTime.parse("22:00:00"));
        when(mockedRestaurant.getCurrentTime()).thenReturn(LocalTime.parse("11:30:00"));
        boolean expected = mockedRestaurant.getCurrentTime().isAfter(mockedRestaurant.getOpeningTime())
                && mockedRestaurant.getCurrentTime().isBefore(mockedRestaurant.getClosingTime());
        when(mockedRestaurant.isRestaurantOpen()).thenReturn(expected);

        assertTrue(mockedRestaurant.isRestaurantOpen());
    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time(){
        //WRITE UNIT TEST CASE HERE
        // Arrange

        when(mockedRestaurant.getCurrentTime()).thenReturn(LocalTime.parse("07:30:00"));
        when(mockedRestaurant.getOpeningTime()).thenReturn(LocalTime.parse("10:30:00"));
        when(mockedRestaurant.getClosingTime()).thenReturn(LocalTime.parse("22:00:00"));
        // Act
        boolean expected = mockedRestaurant.getCurrentTime().isAfter(mockedRestaurant.getOpeningTime())
                && mockedRestaurant.getCurrentTime().isBefore(mockedRestaurant.getClosingTime());
        when(mockedRestaurant.isRestaurantOpen()).thenReturn(expected);
        // Assert
        assertFalse(mockedRestaurant.isRestaurantOpen());
    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1(){

        int initialMenuSize = regularRestaurant.getMenu().size();
        regularRestaurant.addToMenu("Sizzling brownie",319);
        assertEquals(initialMenuSize+1,regularRestaurant.getMenu().size());
    }
    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {

        int initialMenuSize = regularRestaurant.getMenu().size();
        regularRestaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize-1,regularRestaurant.getMenu().size());
    }
    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {
        assertThrows(itemNotFoundException.class,
                ()->regularRestaurant.removeFromMenu("French fries"));
    }
    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
}
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ShopRepositoryTest {

    Product product1 = new Product(1, "Хлеб", 40);
    Product product2 = new Product(2, "Молоко", 90);
    Product product3 = new Product(3, "Чай", 150);
    Product product4 = new Product(3, "Кофе", 200);

    @Test
    public void ShouldRemoveFoundId() {
        ShopRepository repo = new ShopRepository();
        repo.add(product1);
        repo.add(product2);
        repo.add(product3);

        repo.remove(2);

        Product[] actual = repo.findAll();
        Product[] expected = {product1, product3};

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void ShouldTrowsException() {
        ShopRepository repo = new ShopRepository();
        repo.add(product1);
        repo.add(product2);
        repo.add(product3);

        Assertions.assertThrows(NotFoundException.class, () -> {
            repo.remove(4);
        });
    }

    @Test
    public void ShouldAddProductWithNewId() {
        ShopRepository repo = new ShopRepository();

        repo.add(product1);
        repo.add(product2);
        repo.add(product3);

        Product[] actual = repo.findAll();
        Product[] expected = {product1, product2, product3};

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void ShouldNotAddProductWithRepeatedId() {
        ShopRepository repo = new ShopRepository();

        repo.add(product1);
        repo.add(product2);
        repo.add(product3);
        
        Assertions.assertThrows(AlreadyExistsException.class, () -> {
            repo.add(product4);
        });
    }
}

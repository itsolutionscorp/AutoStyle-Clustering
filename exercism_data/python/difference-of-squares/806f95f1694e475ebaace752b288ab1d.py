def square_of_sum(value):
    return (sum(list(range(1, value + 1))))**2

def sum_of_squares(value):
    def power(x):
        return x ** 2
    return sum(map(power, list(range(1, value + 1))))

def difference(value):
    return  square_of_sum(value) - sum_of_squares(value)

if __name__ == '__main__':
    print(difference(100))

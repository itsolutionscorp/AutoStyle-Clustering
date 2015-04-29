class Grains

    def square(num)
        return 1 if num == 1
        squares = get_squares(num)
        return squares.last
    end

    def get_squares(num)
        squares = []
        for n in (1..num)   
            if squares.empty?
                squares.push(1)
            else
                squares.push(squares.last * 2)
            end
        end

        return squares
    end

    def total
        squares = get_squares(64)
        return squares.inject(:+)
    end

end

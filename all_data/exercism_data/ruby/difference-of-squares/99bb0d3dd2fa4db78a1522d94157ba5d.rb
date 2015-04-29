class Squares

    def initialize( n )
        @n = n
    end
    
    def square_of_sums
        # naïve one liner: ( 1..@n ).inject( 0, :+ ) ** 2
        @square_of_sums ||= ( 1..@n ).sum ** 2
    end
    
    def sum_of_squares
        # Square each and sum
        @sum_of_squares ||= ( 1..@n ).map{ |x| x ** 2 }.inject( 0, :+ )
    end
    
    def difference
        square_of_sums - sum_of_squares
    end
    
end

class Range

    def sum( identity = 0)
        actual_last = exclude_end? ? (last - 1) : last
        
        # Way more efficient than injecting +
        if actual_last >= first
            (actual_last - first + 1) * (actual_last + first) / 2
        else
            identity
        end
    end
    
end

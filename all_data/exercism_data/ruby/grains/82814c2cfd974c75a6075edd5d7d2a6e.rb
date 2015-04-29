class Grains

    def square(square_num)
        2 ** (square_num - 1)
    end
    
    def total
        @total ||= (1..64).map {|e| square(e)}.reduce(:+)
    end

end

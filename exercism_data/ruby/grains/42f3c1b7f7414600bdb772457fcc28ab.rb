class Grains
    
    GRAIN_NUMBERS = (0..63).map do |square|
        1 << square
    end

    GRAIN_TOTAL = GRAIN_NUMBERS.reduce(0) do |grains, n|
        grains += n
    end
    
    def square(n)
        GRAIN_NUMBERS[n-1]
    end

    def total
        GRAIN_TOTAL
    end

end

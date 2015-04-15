class Grains
    
    GRAIN_NUMBERS = (0..63).reduce([]) do |grains, square|
        grains.push(1 << square)
        grains
    end

    GRAIN_TOTAL = (0..63).reduce(0) do |grains, n|
        grains += GRAIN_NUMBERS[n]
    end

    def square(n)
        GRAIN_NUMBERS[n-1]
    end

    def total
        GRAIN_TOTAL
    end

end

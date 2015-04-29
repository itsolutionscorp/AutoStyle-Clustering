class Grains
    
    @@grain_numbers = []
    
    (0..63).each do |n|
        @@grain_numbers.push(1 << n)
    end

    @@grain_total = (0..63).reduce(0) do |grains, n|
        grains += @@grain_numbers[n]
    end

    def square(n)
        @@grain_numbers[n-1]
    end

    def total
        @@grain_total
    end

end

class Grains
    def square(n)
        if n == 1
            1
        else
            2 * square(n - 1)
        end
    end
    def total
        (1..64).inject(0) do |sum, idx|
            sum += square(idx)
        end
    end
end

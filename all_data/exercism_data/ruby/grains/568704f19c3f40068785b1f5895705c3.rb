class Grains
    def square(n)
        2**(n-1)
    end

    def total
        @total ||= (1..64).inject{ |t, n| t + square(n) }
    end
end

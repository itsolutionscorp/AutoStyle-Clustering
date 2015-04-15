class Grains
    @@squares = 64

    def square(n)
        2**(n-1)
    end

    def total
        square(@@squares + 1) - 1
    end
end

class Grains
    def square(position)
        raise(ArgumentError) if position == 0
        2 ** (position - 1)
    end

    def total
        2 ** 64
    end
end

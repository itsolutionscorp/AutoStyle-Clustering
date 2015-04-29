class Hamming
    def self.compute(x, y)
        x.split('').zip(y.split('')).count{ |z| arent_the_same(z[0],z[1]) }
    end

    def self.arent_the_same(x,y)
        return x != nil && y != nil && x != y
    end

end

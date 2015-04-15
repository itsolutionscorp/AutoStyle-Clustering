class Hamming
    def self.compute(x, y)
        x.split('').zip(y.split('')).count{ |z| z[0] != nil && z[1] != nil && z[0] != z[1] }
    end

end

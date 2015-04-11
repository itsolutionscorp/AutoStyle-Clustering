class Hamming
    def self.compute(x, y)
        x.chars.zip(y.chars).count{ |a,b| arent_the_same(a,b) }
    end

    def self.arent_the_same(x,y)
        #x can never be nil, since y is zipped into x
        y!=nil && !(x === y)
    end
end

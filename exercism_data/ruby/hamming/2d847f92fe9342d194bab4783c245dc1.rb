class Hamming
    def self.compute a, b
        (b.split(//) - a.split(//)).length
    end
end

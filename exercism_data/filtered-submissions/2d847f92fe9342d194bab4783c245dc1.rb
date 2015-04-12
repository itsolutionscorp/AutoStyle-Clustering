class Hamming
    def compute a, b
        (b.split(//) - a.split(//)).length
    end
end

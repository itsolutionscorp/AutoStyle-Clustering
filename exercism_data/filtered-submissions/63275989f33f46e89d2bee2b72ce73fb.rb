class Hamming
    def compute(a, b)
        ba = b.split(//)
        a.split(//).count {|e| e != ba.shift}
    end
end
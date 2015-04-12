class Hamming

    def Hamming.compute(oldStrand, newStrand)
        oldStrand.chars.zip(newStrand.chars).count{|x| x[0] != x[1]}
    end    

end

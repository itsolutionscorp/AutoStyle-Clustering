class Hamming

    def Hamming.compute(oldStrand, newStrand)

        if oldStrand.eql?(newStrand)
            return 0
        end    

        return oldStrand.chars.zip(newStrand.chars).count{|x| x[0] != x[1]}
    end    

end

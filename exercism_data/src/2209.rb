class Hamming

    def Hamming.compute(oldStrand, newStrand)

        if oldStrand.eql?(newStrand)
            return 0
        end    

        nrOfMutations = 0
        
        oldStrand.chars.each_index {|index| 

            if index >= newStrand.length
                return nrOfMutations
            end

            if oldStrand[index] != newStrand[index]
                nrOfMutations = nrOfMutations +1
            end    
        }
        return nrOfMutations
    end    

end

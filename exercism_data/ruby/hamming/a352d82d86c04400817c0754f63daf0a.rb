class Hamming
    def self.compute(dna1, dna2)
        #compute Hamming distance of 2 DNA strings        
        prep1 = self.prepare dna1 
        prep2 = self.prepare dna2 

        self.calculate prep1, prep2
    end

    def self.prepare(dna)
        #preparation steps
        dna_hash = Hash.new 
        dna.split(//).each_with_index do |index, key|
            dna_hash[key] = index            
        end
        dna_hash
    end    

    def self.calculate(dna1, dna2)
        #if values for the same index are not the same, increase distance            
        distance = 0
        dna1.each do |key, value| 
            unless dna2[key] == value then distance+=1 end
        end
        distance
    end
end

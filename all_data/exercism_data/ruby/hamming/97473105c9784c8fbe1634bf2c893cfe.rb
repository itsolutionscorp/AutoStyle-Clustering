class Hamming 
    def self.compute(h1,h2)
        
        if h1.length > h2.length
            strand_length = h2.length
        else 
            strand_length = h1.length
        end
        
        hamming_distance = 0;

        strand_length.times do |i|
            if h1[i] != h2[i] 
                hamming_distance+=1
            end
        end
        
        hamming_distance
    end
end

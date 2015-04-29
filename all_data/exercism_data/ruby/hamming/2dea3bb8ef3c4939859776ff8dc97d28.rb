class Hamming

    def self.compute(x,y)
        seq1 = x.split('')
        seq2 = y.split('')
        
        difference(seq1,seq2)
    end

    def self.difference(seq1, seq2)
        index = 0
        count = 0
        
        seq1.each do |i|
            if i != seq2[index]
                count += 1
            end
            
            index += 1
        end
        
        count   
    end
    
end

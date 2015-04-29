class Complement
    def self.of_dna(strand_to_check)
        strand_complemented = []
        if strand_to_check.empty? == false
            strand = strand_to_check.each_char.to_a
            for i in 0..strand.size
            	strand_complemented.push case
                when strand[i] == 'C'
                    	'G'
                when strand[i] == 'G'
                    	'C'
                when strand[i] == 'T'
                    	'A'
                when strand[i] == 'A'
                    	'U'
                end
            end
        end
        return strand_complemented.join
        end

    def self.of_rna(strand_to_check)
    strand_complemented = []
   		if strand_to_check.empty? == false
        	strand = strand_to_check.each_char.to_a
        	for i in 0..strand.size
        		strand_complemented.push case
            	when strand[i] == 'C'
            	    	'G'
            	when strand[i] == 'G'
            	    	'C'
            	when strand[i] == 'A'
            	    	'T'
           		when strand[i] == 'U'
            	    	'A'
            	end
        	end
    	end
    	return strand_complemented.join
    end 
end

class Complement

    
    

    # @param dna [String]
    # @ return [String]
    def self.of_dna(dna)
        dna_key = {'G'=>'C', 'C'=>'G', 'T'=>'A', 'A'=>'U'}
        converted = ""
        dna.split("").each {
            |i|
            converted += dna_key[i]
        }
        converted
        
    end

    # @param rna [String]
    # @return [String
    def self.of_rna(rna)
        rna_key = {'C'=>'G', 'G'=>'C', 'A'=>'T', 'U'=>'A'}
        converted = ""
        rna.split("").each {
            |i|
            converted += rna_key[i]
        }
        converted
    end


end

puts Complement.of_dna("AAA")

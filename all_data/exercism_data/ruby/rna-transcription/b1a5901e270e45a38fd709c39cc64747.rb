class Complement
    @@to_rna = {'G'=>'C', 'C'=>'G', 'T'=>'A', 'A'=>'U'} 
    @@to_dna = {'C'=>'G', 'G'=>'C', 'A'=>'T', 'U'=>'A'} 

    def self.of_dna(s)
        return s.chars.map { |c| @@to_rna[c] } .join
    end

    def self.of_rna(s)
        return s.chars.map { |c| @@to_dna[c] } .join
    end
end

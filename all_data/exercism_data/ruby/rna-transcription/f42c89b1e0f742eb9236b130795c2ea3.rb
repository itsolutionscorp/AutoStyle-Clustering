class Complement
    def self.of_dna dna 
      dna = dna.split ""
      result = ""
      dna_strand = {'G' => 'C', 'C' => 'G', 'T' => 'A', 'A'  => 'U'}
        dna.each { |s| result.concat(dna_strand[s].to_s) }
      return result
      #return dna_strand[dna]
       #           dna.inject({}) |result, 
    end

    def self.of_rna rna
      rna_strand = {'C' => 'G', 'G' => 'C', 'A' => 'T', 'U'  => 'A'}
      rna = rna.split ""
      result = ""
        rna.each { |s| result.concat(rna_strand[s]).to_s }
      return result
    end

end

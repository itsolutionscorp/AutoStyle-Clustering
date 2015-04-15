class Complement

  @@dna_lookup = { 'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U' }
  @@rna_lookup = { 'C' => 'G', 'G' => 'C', 'A' => 'T', 'U' => 'A' }

  class << self
    def of_dna(dna)
      convert(dna, @@dna_lookup)
    end

    def of_rna(rna)
      convert(rna, @@rna_lookup)
    end

    def convert(strand, lookup)
      res = []
      strand.chars.each do |char|
        res << lookup[char]
      end
      res.join
    end
  end
  

end

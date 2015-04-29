class Complement
  
  DnaComplements = {'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U'}
  RnaComplements = {'C' => 'G', 'G' => 'C', 'A' => 'T', 'U' => 'A'}
  
  def self.of_dna(dna_strand) 
    if dna_strand.chars.all? {|letter| DnaComplements[letter]}
      dna_strand.chars.map {|dna| DnaComplements[dna] }.join
    else
      raise ArgumentError.new("Incorrect Nucleotide")
    end
  end

  def self.of_rna(rna_strand)
    if rna_strand.chars.all? {|letter| RnaComplements[letter]}
      rna_strand.chars.map {|rna| RnaComplements[rna] }.join
    else
      raise ArgumentError.new("Incorrect Nucleotide")
    end
  end
end



# DNA    RNA
# `G` -> `C`
# `C` -> `G`
# `T` -> `A`
# `A` -> `U`

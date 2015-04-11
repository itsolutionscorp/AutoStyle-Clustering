class Complement
  
  DnaComplements = {'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U'}
  RnaComplements = {'C' => 'G', 'G' => 'C', 'A' => 'T', 'U' => 'A'}
  
  def self.of_dna(dna_strand) 
    if dna_strand.include?('U') 
      raise ArgumentError.new("Incorrect Nucleotide")
    else
      dna_strand.chars.map {|dna| DnaComplements[dna] }.join
    end
  end

  def self.of_rna(rna_strand)
    if rna_strand.include?('T')
      raise ArgumentError.new("Incorrect Nucleotide")
    else
      rna_strand.chars.map {|rna| RnaComplements[rna] }.join
    end
  end
end

# DNA    RNA
# `G` -> `C`
# `C` -> `G`
# `T` -> `A`
# `A` -> `U`

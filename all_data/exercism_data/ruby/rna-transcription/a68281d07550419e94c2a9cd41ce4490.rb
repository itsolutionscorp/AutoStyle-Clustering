class Complement
  def self.of_dna(nuc)
    test = nuc.delete "ACGT"
    if test.empty?
      nuc.gsub!(/[GCTA]/, 'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U')
    else
      raise ArgumentError, 'Argument is not right dna nucleotide'
    end
  end

  def self.of_rna(nuc)
    test = nuc.delete "ACGU"
    if test.empty?
      nuc.gsub!(/[GCUA]/, 'C' => 'G', 'G' => 'C', 'U' => 'A', 'A' => 'T')
    else
      raise ArgumentError, 'Argument is not right dna nucleotide'
    end
  end
end

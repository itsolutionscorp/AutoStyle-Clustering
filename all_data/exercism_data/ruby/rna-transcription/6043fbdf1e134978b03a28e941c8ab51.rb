class Complement
  def self.of_dna(dna)
    raise ArgumentError if not dna.gsub(/[GCTA]/,'').empty?
    dna.gsub(/[GCTA]/,'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U' )
  end

  def self.of_rna(rna)
    raise ArgumentError if not rna.gsub(/[CGAU]/,'').empty?
    rna.gsub(/[CGAU]/,'C' => 'G', 'G' => 'C', 'A' => 'T', 'U' => 'A' )
  end
end

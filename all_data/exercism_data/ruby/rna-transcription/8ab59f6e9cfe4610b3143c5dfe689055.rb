# class to calculate RNA complement from DNA strand
class Complement
  def self.complements
    { 'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U' }
  end

  def self.of_dna(dna)
    dna.gsub(/['G'|'C'|'T'|'A']/, complements )
  end

  def self.of_rna(rna)
    rna.gsub(/['C'|'G'|'A'|'U']/, complements.invert )
  end

end

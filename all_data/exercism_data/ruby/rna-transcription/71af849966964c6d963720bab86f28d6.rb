class Complement
  DNA_COMPLEMENTS = {
    'G' => 'C',
    'C' => 'G',
    'T' => 'A',
    'A' => 'U'
  }
  def self.of_dna(string)
    string.gsub(/[GCTA]/, DNA_COMPLEMENTS)
  end

  def self.of_rna(string)
    string.gsub(/[CGAU]/, DNA_COMPLEMENTS.invert)
  end
end

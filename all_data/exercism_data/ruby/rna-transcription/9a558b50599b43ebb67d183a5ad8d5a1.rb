class Complement

  def self.of_dna(letter)
    rna = letter.gsub!(/[GCTA]/, 'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U')
    rna

  end

  def self.of_rna(letter)
    letter.gsub!(/[CGAU]/, 'C' => 'G', 'G' => 'C', 'A' => 'T', 'U' => 'A')
  end
end

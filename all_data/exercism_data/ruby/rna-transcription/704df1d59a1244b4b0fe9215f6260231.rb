class Complement
  def self.get_complements
    {
      'C' => 'G',
      'G' => 'C',
      'T' => 'A',
      'A' => 'U'
    }
  end

  def self.of_dna(nucleotide)
    dna_complements = get_complements
    nucleotide.chars.map {|letter| dna_complements[letter]}.join
  end

  def self.of_rna(nucleotide)
    rna_complements = get_complements.invert
    nucleotide.chars.map {|letter| rna_complements[letter]}.join
  end

end

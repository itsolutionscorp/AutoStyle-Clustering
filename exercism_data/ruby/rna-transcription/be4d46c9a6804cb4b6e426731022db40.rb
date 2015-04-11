class Complement
  def self.of_dna(strand)
    strand.chars.map do |nucleotide|
      nucleotides[nucleotide]
    end.join
  end

  def self.of_rna(strand)
    strand.chars.map do |nucleotide|
      nucleotides.key(nucleotide)
    end.join
  end

  def self.nucleotides
    {
      'G' => 'C',
      'C' => 'G',
      'T' => 'A',
      'A' => 'U'
    }
  end
end

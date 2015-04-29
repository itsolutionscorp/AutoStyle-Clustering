class Complement
  @nucleotides = {
    'G' => 'C',
    'C' => 'G',
    'T' => 'A',
    'A' => 'U'
  }

  def self.of_dna(strand)
    strand.chars.map { |letter| @nucleotides[letter] }.join
  end

  def self.of_rna(strand)
    strand.chars.map { |letter| @nucleotides.key(letter) }.join
  end
end

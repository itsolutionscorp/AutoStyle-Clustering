class Complement
  @nucleotides = {
    'G' => 'C',
    'C' => 'G',
    'T' => 'A',
    'A' => 'U'
  }

  def self.of_dna(strand)
    complement = strand.chars.map do |letter|
      @nucleotides[letter]
    end
    complement.join
  end

  def self.of_rna(strand)
    complement = strand.chars.map do |letter|
      @nucleotides.key(letter)
    end
    complement.join
  end
end

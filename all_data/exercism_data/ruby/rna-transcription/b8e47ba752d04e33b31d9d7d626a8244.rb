class Complement
  RNA_TRANSLATION = {
    'C' => 'G',
    'G' => 'C',
    'T' => 'A',
    'A' => 'U',
  }

  DNA_TRANSLATION = RNA_TRANSLATION.invert

  def self.of_dna(nucleotides)
    new(nucleotides, RNA_TRANSLATION).complement
  end

  def self.of_rna(nucleotides)
    new(nucleotides, DNA_TRANSLATION).complement
  end

  def initialize(nucleotides, translation)
    @nucleotides = nucleotides
    @translation = translation
  end

  def complement
    @nucleotides.each_char.map { |n| @translation[n] }.join
  end

end

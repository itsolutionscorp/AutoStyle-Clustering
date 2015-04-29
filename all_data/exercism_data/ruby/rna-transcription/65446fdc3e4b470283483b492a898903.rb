class Complement
  CONVERSIONS = {
    'G'=>'C',
    'C'=>'G',
    'T'=>'A',
    'A'=>'U'
  }

  def self.of_dna strand
    translate strand
  end

  def self.of_rna strand
    translate strand, CONVERSIONS.invert
  end

  def self.translate strand, conversions=CONVERSIONS
    strand
      .each_char
      .map { |nucleotide| conversions[nucleotide] }
      .join
  end
end

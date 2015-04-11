class Complement
  CONVERT = {
    'G' => 'C',
    'C' => 'G',
    'T' => 'A',
    'A' => 'U'
  }

  @rna_terms = CONVERT.keys.join("|")
  @rna_pattern = Regexp.new(@rna_terms)
  @dna_terms = CONVERT.values.join("|")
  @dna_pattern = Regexp.new(@dna_terms)

  def self.of_dna rna
    rna.gsub(@rna_pattern, CONVERT)
  end

  def self.of_rna dna
    dna.gsub(@dna_pattern, CONVERT.invert)
  end

end

class Complement
  def self.of_dna(str)
    str.chars.map { |e| rna_element e }.join
  end

  def self.of_rna(str)
    str.chars.map { |e| dna_element e }.join
  end

  def self.rna_element(dna_el)
    { 'G' => 'C',
      'C' => 'G',
      'T' => 'A',
      'A' => 'U' }[dna_el]
  end

  def self.dna_element(rna_el)
    { 'C' => 'G',
      'G' => 'C',
      'A' => 'T',
      'U' => 'A' }[rna_el]
  end
end

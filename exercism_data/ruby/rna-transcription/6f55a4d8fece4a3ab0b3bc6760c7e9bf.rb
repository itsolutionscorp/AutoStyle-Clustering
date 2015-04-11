class Complement
  def self.of_dna(str)
    str.chars.map { |char| convert_dna_from[char] }.join
  end

  def self.of_rna(str)
    str.chars.map { |char| convert_rna_from[char] }.join
  end

  private
  def self.convert_dna_from
    {
      'C' => 'G',
      'G' => 'C',
      'T' => 'A',
      'A' => 'U'
    }
  end

  def self.convert_rna_from
    {
      'C' => 'G',
      'G' => 'C',
      'U' => 'A',
      'A' => 'T'
    }
  end
end

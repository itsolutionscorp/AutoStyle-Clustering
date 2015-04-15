class Complement

  DNA = {
      'C' => 'G',
      'G' => 'C',
      'T' => 'A',
      'A' => 'U'
  }

  RNA = {
      'C' => 'G',
      'G' => 'C',
      'U' => 'A',
      'A' => 'T'
  }


  def self.of_dna(str)
    str.chars.map { |char| DNA[char] }.join
  end

  def self.of_rna(str)
    str.chars.map { |char| RNA[char] }.join
  end
end

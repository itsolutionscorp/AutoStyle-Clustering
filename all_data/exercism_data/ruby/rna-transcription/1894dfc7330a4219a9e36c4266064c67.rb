class Complement
  def self.of_dna(input)
    mapping = {
      'G' => 'C',
      'C' => 'G',
      'T' => 'A',
      'A' => 'U'
    }

    input.chars.map { |c| mapping[c] }.join
  end

  def self.of_rna(input)
    mapping = {
      'C' => 'G',
      'G' => 'C',
      'A' => 'T',
      'U' => 'A'
    }

    input.chars.map { |c| mapping[c] }.join
  end
end

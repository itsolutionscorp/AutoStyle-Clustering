class Complement

  def self.of_dna(nucleotides)
    pairs = {
      'G' => 'C',
      'C' => 'G',
      'T' => 'A',
      'A' => 'U'
    }

    match(nucleotides, pairs)
  end

  def self.of_rna(nucleotides)
    pairs = {
      'A' => 'T',
      'C' => 'G',
      'G' => 'C',
      'U' => 'A'
    }

    match(nucleotides, pairs)
  end

  private

  def self.match(nucleotides, pairs)
    nucleotides.chars.map { |n| pairs.fetch(n) }.join
  end
end

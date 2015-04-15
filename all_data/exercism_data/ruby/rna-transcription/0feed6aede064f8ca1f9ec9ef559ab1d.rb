class Complement
  def self.of_dna(strand)
    map = {
      'G' => 'C',
      'C' => 'G',
      'T' => 'A',
      'A' => 'U'
    }

    strand.chars.collect { |nucleotide, index| map[nucleotide] }.join
  end

  def self.of_rna(strand)
    map = {
      'G' => 'C',
      'C' => 'G',
      'U' => 'A',
      'A' => 'T'
    }

    strand.chars.collect { |nucleotide, index| map[nucleotide] }.join
  end
end

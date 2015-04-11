class Complement

  def self.of_rna(strand)
    base_pairs = {
      'G' => 'C',
      'C' => 'G',
      'A' => 'T',
      'U' => 'A'
    }
    @strand = strand.split('')
    @strand.map { |nucleotide| base_pairs[nucleotide] }.join
  end

  def self.of_dna(strand)
    base_pairs = {
      'G' => 'C',
      'C' => 'G',
      'T' => 'A',
      'A' => 'U'
    }

    @strand = strand.split('')
    @strand.map { |nucleotide| base_pairs[nucleotide] }.join
  end
end

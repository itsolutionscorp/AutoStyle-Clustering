class Complement
  def self.of_dna(strand)
    mapping = {
      'G' => 'C',
      'C' => 'G',
      'T' => 'A',
      'A' => 'U'
    }
    Complement.convert(strand, mapping)
  end

  def self.of_rna(strand)
    mapping = {
      'C' => 'G',
      'G' => 'C',
      'A' => 'T',
      'U' => 'A'
    }
    Complement.convert(strand, mapping)
  end

  def self.convert(strand, mapping)
    strand.chars.map{ |nucleotide| mapping[nucleotide] }.join
  end
end

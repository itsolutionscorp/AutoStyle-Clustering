class Complement
  def self.of_dna(strand)
    mapping = { 'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U' }
    convert(strand, mapping)
  end

  def self.of_rna(strand)
    mapping = { 'G' => 'C', 'C' => 'G', 'U' => 'A', 'A' => 'T' }
    convert(strand, mapping)
  end

  def self.convert(strand, mapping)
    strand.chars.map{ |nucleotide| mapping[nucleotide] }.join
  end
end

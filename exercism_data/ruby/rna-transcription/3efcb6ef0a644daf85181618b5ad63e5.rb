class Complement
  def self.of_dna(strand)
    map_strand(strand, pairing)
  end

  def self.of_rna(strand)
    map_strand(strand, pairing.invert)
  end

  def self.map_strand(strand, conversion_map)
    strand.chars.map do |nucleotide|
      conversion_map[nucleotide.to_sym].to_s
    end.join
  end

  def self.pairing
    {
      G: :C,
      C: :G,
      T: :A,
      A: :U
    }
  end

  private_class_method :map_strand
  private_class_method :pairing
end

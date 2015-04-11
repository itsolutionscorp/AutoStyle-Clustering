class Hamming

  def self.compute(strand_one, strand_two)
    strand_one = strip_extra_bases_from(strand_one, strand_two.length - 1) if strand_one.length > strand_two.length

    array_of(strand_one).each_with_index.inject(0) do |sum, (base, position)|
      same_base_in?(strand_two, base, position) ? sum + 1 : sum
    end
  end

  def self.strip_extra_bases_from(strand, length)
    strand = strand[0..length]
  end

  def self.array_of(strand)
    strand.chars
  end

  def self.same_base_in?(strand, base, position)
    array_of(strand)[position] != base
  end

end

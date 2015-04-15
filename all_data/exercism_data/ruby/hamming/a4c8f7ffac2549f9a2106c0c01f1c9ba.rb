class Hamming
  def self.compute(strand, other)
    new(strand).distance_from(other)
  end

  def initialize(strand)
    @original = strand
  end

  def distance_from(strand)
    unique_pairs(strand).reject { |pair| pair.length == 1 }.length
  end

  private

  def unique_pairs(strand)
    zip_strands(strand).map do |paired_nucleotides|
      paired_nucleotides.compact.uniq
    end
  end

  def zip_strands(strand)
    @original.chars.zip(strand.chars)
  end
end

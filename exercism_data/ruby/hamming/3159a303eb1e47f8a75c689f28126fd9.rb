class Hamming
  attr_reader :strand_a, :strand_b

  def self.compute(a, b)
    new(Strand.new(a), Strand.new(b)).compute
  end

  def initialize(strand_a, strand_b)
    @strand_a = strand_a
    @strand_b = strand_b
  end

  def compute
    strand_a.length.times.count do |index|
      strand_b[index] && !equal_at?(index)
    end
  end

  def equal_at?(index)
    strand_a[index] == strand_b[index]
  end

end

class Strand
  attr_reader :sequence

  def initialize(sequence)
    @sequence = sequence.split('')
  end

  def length
    sequence.length
  end

  def [](index)
    sequence[index]
  end
end

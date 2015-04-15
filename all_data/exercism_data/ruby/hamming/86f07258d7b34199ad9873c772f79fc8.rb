class Hamming
  
  def self.compute(strand1, strand2)
    HammingCalculator.new(strand1, strand2).difference
  end

end

class HammingCalculator

  attr_accessor :long_strand, :short_strand

  def initialize(strand1, strand2)
    if strand1.length > strand2.length
      @long_strand = strand1
      @short_strand = strand2
    else
      @long_strand = strand2
      @short_strand = strand1
    end
  end

  def common_length
    @common_length ||= short_strand_as_array.length
  end

  def long_strand_as_array
    @long_strand_array ||= long_strand.chars
  end

  def short_strand_as_array
    @short_strand_array ||= short_strand.chars
  end

  def difference
    common_length.times.select do |idx|
      long_strand_as_array[idx] != short_strand_as_array[idx]
    end.count
  end
end

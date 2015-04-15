class Hamming
  def self.compute(sequence_a, sequence_b)
    Hamming.new(sequence_a, sequence_b).compute
  end

  def initialize(sequence_a, sequence_b)
    @sequence_a = sequence_a
    @sequence_b = sequence_b
    @hamming_distance = 0
  end

  def compute
    common_sequence_length.times do |index|
      compare_pair_at(index)
    end
    @hamming_distance
  end

  def common_sequence_length
    [@sequence_a.length, @sequence_b.length].min
  end

  def compare_pair_at(index)
    if @sequence_a[index] != @sequence_b[index]
      @hamming_distance += 1
    end
  end
end

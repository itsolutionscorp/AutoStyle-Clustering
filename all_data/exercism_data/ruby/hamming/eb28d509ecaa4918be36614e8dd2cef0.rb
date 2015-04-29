class Hamming
  def self.compute(seq_1, seq_2)
    pairs = pair_up(seq_1, seq_2)

    pairs.inject(0) do |hamming, tuple|
      hamming += compare(tuple.first, tuple.last)
    end
  end

  private

  def self.pair_up(seq_1, seq_2)
    seq_1_points = seq_1.chars
    seq_2_points = seq_2.chars
    paired = seq_1_points.zip(seq_2_points)
    make_equal_length(paired)
  end

  def self.make_equal_length(paired)
    paired.delete_if {|pair| !(pair.first && pair.last)}
  end

  def self.compare(f, s)
    f == s ? 0 : 1
  end
end

class Hamming < Struct.new(:strand_a, :strand_b)
  def self.compute(strand_a, strand_b)
    Hamming.new(strand_a, strand_b).compute
  end

  def compute
    (0..max_length).inject(0) do |sum, index|
      sum + change_length_at_index(index)
    end
  end

  def change_length_at_index(index)
    strand_a[index] == strand_b[index] ? 0 : 1
  end

  def max_length
    ([strand_a.length, strand_b.length].min)-1
  end
end

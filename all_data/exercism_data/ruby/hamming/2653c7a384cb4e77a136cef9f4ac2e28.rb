class Hamming
  class << self
    def compute(strand_a, strand_b)
      raise "Strands must be similar length" unless strand_a.length == strand_b.length
      strand_a.chars.zip(strand_b.chars).count{|a, b| a != b}
    end
  end
end

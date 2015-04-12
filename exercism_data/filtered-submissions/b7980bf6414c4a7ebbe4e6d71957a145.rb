# class to compute Hamming difference in 2 DNA strands
class Hamming
  def compute(strand_one, strand_two)
    differences = 0
    strand_one.chars.each_with_index do |symbol, idx|
      differences += 1 unless symbol == strand_two[idx]
    end
    differences
  end
end

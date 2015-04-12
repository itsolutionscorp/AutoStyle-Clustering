class Hamming
  def compute(reference_strand, other_strand)
    common_length = [reference_strand.size, other_strand.size].min
    common_length.times.count { |index| reference_strand[index] != other_strand[index] }
  end
end

# Calculate the Hamming difference between two DNA strands
class Hamming

  def self.compute(strand_a, strand_b)
    # Compare the strands only up to the 
    # length of the shortest strand
    check_length = [strand_a.length, strand_b.length].min
    (0...check_length).count { |c| strand_a[c] != strand_b[c] }
  end
end

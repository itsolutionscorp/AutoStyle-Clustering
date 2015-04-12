# Calculate the Hamming difference between two DNA strands
class Hamming

  def self.compute(strand_a, strand_b)
    # Compare the strands only up to the 
    # length of the shortest strand
    diff = 0
    strand_a.chars.each_with_index do |c, index|
      # Break out if strand_b is shorter than strand_a
      break if strand_b[index].nil?
      diff += 1 unless c == strand_b[index]
    end
    diff
  end
end
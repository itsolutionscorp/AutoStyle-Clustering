class Hamming
  def compute primary_strand, secondary_strand
    [primary_strand.length, secondary_strand.length]
    .min
    .times
    .count { |i|  primary_strand[i] != secondary_strand[i] }
  end
end

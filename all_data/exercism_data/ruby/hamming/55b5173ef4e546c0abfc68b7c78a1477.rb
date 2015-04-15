#
class Hamming
  def self.equalize_strand_lengths(left, right)
    left = left.take(right.size) if left.size > right.size
    [left, right]
  end

  def self.compute(left_strand, right_strand)
    left_strand, right_strand = [left_strand, right_strand].map(&:chars)
    left_strand, right_strand = equalize_strand_lengths(left_strand, right_strand)
    left_strand.zip(right_strand).map { |s1, s2| s1 == s2 }.count(false)
  end
end

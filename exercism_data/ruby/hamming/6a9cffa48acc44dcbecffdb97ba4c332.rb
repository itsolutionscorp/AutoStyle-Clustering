#
class Hamming
  def self.equalize_strand_length(left, limit)
    left.size > limit ? left.take(limit) : left
  end

  def self.compute(left_strand, right_strand)
    left_strand, right_strand = [left_strand, right_strand].map(&:chars)
    left_strand = equalize_strand_length(left_strand, right_strand.size)
    left_strand.zip(right_strand).count { |s1, s2| s1 != s2 }
  end
end

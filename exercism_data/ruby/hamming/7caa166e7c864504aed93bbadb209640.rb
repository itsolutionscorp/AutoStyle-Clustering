module CharSequenceString
  refine String do
    def char_difference(other)
      char_count = [self.length, other.length].max
      (0..char_count).count { |n| self.chars[n] != other.chars[n] }
    end
  end
end


class Hamming
  using CharSequenceString

  def self.compute(strand1, strand2)

    raise ArgumentError, "The Hamming distance is only defined for sequences of equal length." if strand1.length != strand2.length

    strand1.char_difference(strand2)
  end
end

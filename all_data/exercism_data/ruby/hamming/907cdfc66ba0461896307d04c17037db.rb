module CharSequenceString
  refine String do
    def char_difference(other)
      difference = 0

      self.chars.each_with_index do |char, index|
        difference += 1 if char != other[index]
      end

      difference
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

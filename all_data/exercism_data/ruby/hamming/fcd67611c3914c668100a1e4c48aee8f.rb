module StringExtensions

  refine String do
    def to_a
      chars.to_a
    end
  end

end

class Hamming

  using StringExtensions

  class << self
    def compute(sequence, other)
      sequence, other = sequence.to_a, other.to_a

      sequence.zip(other).count do |element, other|
        element != other && other != nil
      end
    end
  end

end

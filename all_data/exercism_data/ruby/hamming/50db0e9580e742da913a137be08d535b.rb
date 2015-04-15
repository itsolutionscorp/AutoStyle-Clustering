class Hamming
  class << self

    # Computes the hamming distance between two strands
    def compute(a, b)
      max_length = [a.length, b.length].max

      hamming_distance = 0
      max_length.times { |i| hamming_distance += 1 if different?(a[i], b[i]) }
      hamming_distance
      a.chars.zip(b.chars).count { |x, y| y && x != y }
    end

    # Compares two nucleotides
    def different?(a, b)
      return false if a.nil?
      return false if b.nil?
      a != b
    end

  end
end

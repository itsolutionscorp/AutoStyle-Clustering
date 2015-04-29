class String
  def blank?
    self.strip.length == 0
  end
end

class Hamming
  def self.compute(s1, s2)
    validate_input(s1, s2)

    s1.split("").each_with_index.inject(0) do |distance, (_acid, idx)|
      distance += 1 unless s1[idx] == s2[idx]
      distance
    end
  end

  private

  def self.only_one_strand?(s1, s2)
    [s1, s2].any? &:blank?
  end

  def self.strands_length_identical(s1, s2)
    s1.length == s2.length
  end

  def self.validate_input(s1, s2)
    return 0 if only_one_strand? s1, s2
    raise ArgumentError, "Strands must be of equal length" unless strands_length_identical s1, s2
  end
end

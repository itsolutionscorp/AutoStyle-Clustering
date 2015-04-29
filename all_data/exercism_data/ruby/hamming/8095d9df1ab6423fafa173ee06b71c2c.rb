class Hamming
  def self.compute(lhs, rhs)
    corresponding_nucleotides_for(lhs, rhs).reduce(0) do |distance, corresponding_pair|
      distance + determine_difference_between(*corresponding_pair)
    end
  end

  private

  def self.corresponding_nucleotides_for(lhs, rhs)
    lhs.chars.zip(rhs.chars)
  end

  def self.determine_difference_between(lhs, rhs)
    lhs == rhs ? 0 : 1
  end
end

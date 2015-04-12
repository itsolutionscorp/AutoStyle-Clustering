class Hamming
  def compute(s1, s2)
    s1.chars.zip(s2.chars)
            .reject { |a| a.any?(&:nil?) }
            .count { |c| c.first != c.last }
  end
end

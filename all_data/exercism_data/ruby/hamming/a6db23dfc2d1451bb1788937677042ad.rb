class Hamming

  def self.compute(s1, s2)
    s1.chars.zip(s2.chars)
            .reject { |a, b| b.nil? }
            .map { |a, b| a == b ? 0 : 1 }
            .reduce(:+)
  end

end

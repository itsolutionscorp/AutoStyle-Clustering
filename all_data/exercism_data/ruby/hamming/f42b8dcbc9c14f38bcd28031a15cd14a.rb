module Hamming
  def self.compute(s1, s2)
    s1.chars.each_with_index.reject { |c, i| c == s2[i] }.length
  end
end

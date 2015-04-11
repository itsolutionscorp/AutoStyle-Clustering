module Hamming
  def self.compute(a, b)
    differences = 0
    a.chars.each_with_index do |c, i|
      differences += 1 if c != b[i]
    end
    differences
  end
end

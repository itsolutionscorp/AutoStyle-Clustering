require 'pry'

class Hamming
  def self.compute(a, b)
    a.chars.each_with_index.reduce(0) do |sum, (char, i)|
      char != b[i] ? sum + 1 : sum
    end
  end
end

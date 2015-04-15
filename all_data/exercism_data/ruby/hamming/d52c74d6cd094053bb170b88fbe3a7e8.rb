# Top level documentation comment?
class Hamming
  def initialize
  end

  def self.compute(l1, l2)
    l2_split = l2.chars
    hamming_score = 0
    l1.chars.each_with_index do |char, i|
      return hamming_score unless l2_split[i]
      char == l2_split[i] ? next : hamming_score += 1
    end
    hamming_score
  end
end

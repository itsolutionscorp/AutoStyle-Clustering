# Compute the Hamming distance between two Strings. See
# http://rosalind.info/problems/hamm/
class Hamming
  def self.compute(a, b)
    shorter, longer = a, b
    shorter, longer = b, a if b.length < a.length
    other_chars = longer.each_char.to_a
    index = -1
    shorter.chars.reduce(0) do |hamming, char|
      index += 1
      char != other_chars[index] ? hamming + 1 : hamming
    end
  end
end

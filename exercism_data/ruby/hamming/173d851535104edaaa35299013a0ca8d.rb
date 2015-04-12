class Hamming
  def self.compute(a, b)
    shorter, longer = a, b
    shorter, longer = b, a if b.length < a.length
    other_chars = longer.each_char.to_a
    index = -1
    shorter.chars.inject(0) do |hamming, current|
      index = index + 1
      if current != other_chars[index]
        hamming + 1
      else
        hamming
      end
    end
  end
end
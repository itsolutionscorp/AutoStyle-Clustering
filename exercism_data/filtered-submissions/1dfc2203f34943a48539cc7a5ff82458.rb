class Hamming
  def compute(a, b)
    shorter, longer = a, b
    shorter, longer = b, a if b.length < a.length
    other_chars = longer.each_char.to_a
    index = -1
    shorter.chars.inject(0) do |hamming, current|
      index = index + 1
      current != other_chars[index] ? hamming + 1 : hamming
    end
  end
end

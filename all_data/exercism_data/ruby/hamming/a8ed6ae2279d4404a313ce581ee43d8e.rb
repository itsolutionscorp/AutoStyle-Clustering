class Hamming
  def self.compute(a, b)
    a, b = a.chars, b.chars
    a.slice!(0..b.length) if a.length > b.length
    a.keep_if.with_index { |ch, index| b[index] != ch }.length
  end
end

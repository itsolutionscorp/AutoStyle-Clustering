class Hamming
  class << self
    def compute(x, y)
      x_chars          = x.split('')
      y_chars          = y.split('')
      difference = 0

      x_chars.length.times { |i| difference += 1 if x_chars[i] != y_chars[i] }
      difference
    end
  end
end

class Hamming
  class << self
    def compute(string_x, string_y)
      difference = 0
      string_x.length.times { |i| difference += 1 if string_x[i] != string_y[i] }
      difference
    end
  end
end

class Hamming
  def self.compute(x, y)
    count = 0
    x_array, y_array = x.chars, y.chars

    x_array.size.times { |i| count += 1 unless x_array[i] == y_array[i] }

    count
  end
end

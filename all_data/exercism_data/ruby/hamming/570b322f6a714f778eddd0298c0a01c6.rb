class Hamming
  def self.compute(string_a, string_b)
    upper_bound = [string_a.length, string_b.length].min
    count = 0
    (0...upper_bound).each { |n| count += 1 if string_a[n] != string_b[n] }
    count
  end
end

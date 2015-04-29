class Hamming
  def self.compute(a, b)
    min_length = [a.length, b.length].min
    max_length = [a.length, b.length].max

    start_differences = (0..min_length).find_all {|i|
      a[i] != b[i]}.length

    return max_length - min_length + start_differences
  end
end

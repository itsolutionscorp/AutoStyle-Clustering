class Hamming

  def self.compute(a, b)
    a = split_string(a)
    b = split_string(b)

    compare_strings(a, b)
  end

  def self.compare_strings(a, b)
    count = 0
    a.each_index do |i|
      count = count + 1 unless a[i] == b[i]
    end
    count
  end

  def self.split_string(str)
    str.split(//)
  end

end

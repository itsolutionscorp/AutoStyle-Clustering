class Hamming

  def compute(string1, string2)
    count = 0
    [string1.size, string2.size].min.times do |i|
      count += 1 unless string1[i] == string2[i]
    end
    count
  end
end

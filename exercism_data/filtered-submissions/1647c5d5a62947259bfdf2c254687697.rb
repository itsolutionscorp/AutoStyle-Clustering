class Hamming
  def compute(string1, string2)
    count = 0
    string1.size.times do |pos|
      count += 1 if string1[pos] != string2[pos]
    end
    count
  end
end

class Hamming
  def compute(s1, s2)
    distance = 0
    zipped = s1.chars.zip(s2.chars)
    zipped.each do |i|
      break          if i[0].nil? || i[1].nil?
      distance += 1 if i[0] != i[1]
    end
    return distance
  end
end

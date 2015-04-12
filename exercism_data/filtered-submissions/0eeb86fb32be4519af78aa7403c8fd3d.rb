class Hamming
  def compute(a,b)
    distance = 0
    a.chars.each_with_index do |char, index|
      distance = distance + 1 unless char == b[index]
    end
    distance
  end

end

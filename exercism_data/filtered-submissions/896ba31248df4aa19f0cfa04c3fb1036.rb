class Hamming

  def compute(l1,l2)
    distance = 0
    l1.each_char.with_index do|char, index|
      distance +=1  if char != l2[index]
    end
    distance
  end

end

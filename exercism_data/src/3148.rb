class Hamming
  def compute(left,right)
    distance = 0
    left.chars.each_with_index do |item,i|
      distance += 1 if left[i] != right[i]
    end 
    distance
  end
end

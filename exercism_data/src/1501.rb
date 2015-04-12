class Hamming
  def compute(a,b)
    hamming_distance = 0
    a.chars.each_with_index do |strand, index|
      hamming_distance += 1 if b[index] && strand != b[index]
    end
    hamming_distance
  end
end

class Hamming
  def compute(a,b)
    hamming = 0
    [a,b].min.each_char.with_index do |char, index|
      hamming += 1 if char != [a,b].max[index]
    end
    hamming
  end
end
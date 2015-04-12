class Hamming
  
  def compute(first, second)
    if ((first.nil? && second.nil?) || (first.empty? && second.empty?))
      0
    else
      now = 1 if first[0] != second[0]
      now = 0 if first[0] == second[0]
      now + Hamming.compute(first[1..-1], second[1..-1])
    end
  end
end

class Hamming
  def compute(s, t)
    a = s.scan /\w/
    b = t.scan /\w/
    distance = 0
    a.each_with_index { |item, i| distance += 1 if !b[i].nil? && item != b[i] }
    
    distance
  end
end

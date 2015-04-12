class Hamming
  def compute(s1, s2)
    if s1.length == s2.length then
      count = 0
      a2 = s2.split(//)
      s1.split(//).each_with_index do |char, idx| 
        count += 1 if char != a2[idx] 
      end
      count
    else
      nil
    end    
  end
end

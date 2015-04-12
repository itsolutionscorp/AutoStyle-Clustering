class Hamming
  def compute(one, two)
    count = 0
    
    length = one.length > two.length ? one.length : two.length
    
    (0..length).each do |i|
      if one[i] == nil || two[i] == nil
        break
      end
      
      count += 1 unless one[i] == two[i]
    end
    
    return count
  end
end

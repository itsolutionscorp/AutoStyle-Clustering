class Hamming
  def compute(s1,s2)
     distance = 0
     string_size = s1.size
     (0 .. string_size).each do |i|
        if s1[i] != s2[i]
         distance +=1
        end

     end
     return distance    
  end
end

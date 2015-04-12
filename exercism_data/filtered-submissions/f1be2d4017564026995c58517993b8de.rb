class Hamming
   def compute(s1, s2)
      length = [s1.length, s2.length].min
      distance = 0
      (0...length).each do |i|
         if s1[i] != s2[i] then distance += 1 end
      end
      distance
   end
end

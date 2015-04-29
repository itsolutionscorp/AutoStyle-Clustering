class Hamming
   def self.compute(s1, s2)
      length = [s1.length, s2.length].min
      counter = 0
      (0...length).each do |i|
         if s1[i] != s2[i] then counter += 1 end
      end
      counter
   end
end

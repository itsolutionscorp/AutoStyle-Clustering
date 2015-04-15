 class Hamming
   def self.compute(s1, s2)
     difference = 0
     (0..s1.length).each do |index|
       difference += 1 unless s1[index] == s2[index]
     end
     difference
   end
 end

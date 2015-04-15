class Hamminh
   def self.min_length(fiber1, fiber2)
       [fiber1.length, fiber2.length].min
   end 
   
   
   def self.distance(fiber1, fiber2, min_length)
       distance = 0
       fiber1.chars[0, min_length].zip(fiber2.chars).each do |fiber1, fiber2|
          distance += 1 if !(fiber1.eql?fiber2)
       end
       distance
   end


   def self.compute(fiber1, fiber2)
       min_length = self.min_length(fiber1, fiber2)
       self.distance(fiber1, fiber2, min_length)
   end   
end 

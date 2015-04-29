class Hamming

   def self.compute(a,b)
      # Limit the number of characters to compare to the length of the shortest string 
      limit = a.length > b.length ? b.length : a.length

      hamm = 0
      # Compare each letter in same position of each string 
      # Unless letters match, increment Hamming Distance by 1
      (0...limit).each do |i|
        hamm +=1 unless a[i] == b[i]
      end 

      hamm
   end

end

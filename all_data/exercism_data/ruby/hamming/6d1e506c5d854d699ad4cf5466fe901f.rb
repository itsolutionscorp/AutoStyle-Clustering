# class Hamming
#   def self.compute(strand1, strand2)
#     (0...[strand1.length, strand2.length].min).count do |i|
#       strand1[i] != strand2[i]
#     end 
#   end
# end

class Hamming
	def self.compute(strand1, strand2)
      (0...[strand1.length, strand2.length].min).count do |i|
        strand1[i] != strand2[i]
      end
  	end
end
    

#I admit right here and now that I copied this code directly from github. I had no idea where to start,
#but I was able to learn a lot by seeing how Katrina wrote it!

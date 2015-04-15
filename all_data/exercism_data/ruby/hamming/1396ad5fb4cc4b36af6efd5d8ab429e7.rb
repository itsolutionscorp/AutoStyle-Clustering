 class Hamming 
 	def self.compute(str1, str2)
 		(str1.length <= str2.length) ? count_difference(str1, str2) : count_difference(str2, str1)
 	end
 	def self.count_difference(str1, str2)
 		str1.chars.each_with_index.inject(0) do |sum, (ch, i)| 
 			if (ch != str2[i]) 
 				sum += 1 
 			end
 			sum
 		end
 	end
 end

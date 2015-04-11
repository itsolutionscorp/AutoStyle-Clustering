 class Hamming 
 	def self.compute(str1, str2)
 		(str1.length <= str2.length) ? countDifference(str1, str2) : countDifference(str2, str1)
 	end
 	def self.countDifference(str1, str2)
 		count = 0
 		str1.split('').each_with_index.map do |ch, i| 
 			if (ch != str2[i]) 
 				count += 1 
 			end
 		end
 		count
 	end
 end

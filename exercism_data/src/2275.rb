class Hamming
	def compute(str1, str2)
		hamming_distance = 0
		[str1.length, str2.length].min.times do |i|
		#min_length.times do |i|
		  if str1[i] != str2[i]
		  	hamming_distance += 1
			end
		end
		hamming_distance
	end
end


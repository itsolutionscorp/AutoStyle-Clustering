require 'pry'

class Hamming
		
	def self.compute(strand1, strand2)

		dissimilarity_count = 0

		if strand1.length > strand2.length
			smallest_string_length = strand2.length
		elsif strand1.length < strand2.length
			smallest_string_length = strand1.length
		else
			smallest_string_length = strand1.length
		end

		smallest_string_length.times do |i|
			if strand1[i] != strand2[i]
				dissimilarity_count += 1 
			end
		end

		dissimilarity_count

	end

end











		# string1.chars.each_index do |i|
		# 	if string1[i] != string2[i]
		# 		d_count += 1 
		# 	end
		# end







	# def compute(string1, string2)
	# 	arr = [string1, string2]
	# 	d_count = 0
	# 	d = []

		# string1.each_char do |char|
		# 	d_count += 1 if char == string2[char]
		# end

	# 	string1.each_char do |char|
	# 		d << char if char != string2[char]
	# 	end


	# 	number_of_string_d = d.count

	# 	binding.pry










	# 	if arr.uniq.count == 1
	# 		0
	# 	elsif dissimilarity_count < string1.length
	# 		1
	# 	elsif dissimilarity_count < string1.length - 1
	# 		2
	# 	else
	# 		1
	# 	end

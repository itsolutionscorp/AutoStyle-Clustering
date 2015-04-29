module Hamming

	def self.compute left, right
		diff_counter = 0
		first.each_char.with_index do |f, i|
			break if second[i].nil?
			diff_counter += 1 if f != second[i]
		end
		diff_counter
	end

end

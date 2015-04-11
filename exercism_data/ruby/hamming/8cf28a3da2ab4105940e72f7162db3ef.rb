require 'pry'

class Hamming
	def self.compute(string1, string2)
		arr1 = string1.split('')
		arr2 = string2.split('')

		i = 0
		tally = 0

		arr1.each.with_index do |letter, i|
			if i < arr1.length && i < arr2.length
				if letter != arr2[i]
					tally = tally + 1
				end
			end
		end

		return tally
	end
end

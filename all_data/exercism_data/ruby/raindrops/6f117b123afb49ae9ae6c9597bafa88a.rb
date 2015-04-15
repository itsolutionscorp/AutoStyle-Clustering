=begin Pseudocode
Refactor the code. It is hard to change this for the future as it is. Might change the % and the matching strings. Make that a hash.

What other logic can we abstract? What about the logic that we are testing the numbers against? Write a method that checks if it is prime.

Think Pooder: how can I write the code so that it is resilient to change in the future. That means dry code - code where the information (and the manipulation of the information) are isolated.
=end

class Raindrops

	@@raindrop_hash = {3 => "Pling", 5 => "Plang", 7 => "Plong"}

	def self.convert(number)
		@number = number #i do this so that I can change this in one place and it should affect anywhere with the @number
		string = ""
		@@raindrop_hash.each_pair do |key, value|
			if is_factor?(key)
				string += value
			end
		end
		string == "" ? number.to_s : string
	end

	def self.is_factor?(factor)
		return true if @number % factor == 0 
	end

end

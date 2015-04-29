#Raindrops is like fizzbuzz, but with precipitation! if prime factors are 3,5,7 we ouput pling, plang and plong, respectively. if none are true, we output the number itself (as a string)
class Raindrops

	def self.convert( num )
		drops = ''
		drops << "Pling" if num % 3 == 0
		drops << "Plang" if num % 5 == 0
		drops << "Plong" if num % 7 == 0
		drops << num.to_s if num % 3 != 0 && num % 5 != 0 && num % 7 != 0
		drops
	end

end

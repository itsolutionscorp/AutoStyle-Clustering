class Raindrops

	PRIME_TEXT = {3 => "Pling", 5 => "Plang", 7 => "Plong"}

	def self.convert(number)
		sounds = PRIME_TEXT.reduce("") do |string, (key, text)|
			(number % key == 0) ? string << text : string
		end
		!sounds.empty? ? sounds : number.to_s
	end

end

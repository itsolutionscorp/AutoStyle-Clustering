class Raindrops
	def self.convert(number)
		rules = {3 => "Pling", 5 => "Plang", 7 => "Plong"}
		raindrop = ""
		rules.each{ |factor, sound| raindrop += sound if is_factor?(factor, number) }
		raindrop += number.to_s if raindrop.empty?
		raindrop
	end

	def self.is_factor?(potential_factor, number)
		number % potential_factor == 0
	end
end

class Raindrops
	SOUND_PAIRS = {
	  3 => 'Pling',
    5 => 'Plang',
    7 => 'Plong'
	}
	def self.convert(number)
	answer = SOUND_PAIRS.each_with_object("") do |(divider, sound), answer|
		answer << sound if number % divider == 0
	end
		answer.empty? ? answer << number.to_s : answer
	end
end

require 'prime'

class Raindrops

	@RainSong = {3 => 'Pling', 5 => 'Plang', 7 => 'Plong'}

	def self.convert(in_value)
		keys_rainsong = Prime.prime_division(in_value).flatten.uniq & @RainSong.keys
		if keys_rainsong.empty?
			"#{in_value}"
		else
			keys_rainsong.collect { |e| @RainSong[e] }.join
		end
				
	end
end

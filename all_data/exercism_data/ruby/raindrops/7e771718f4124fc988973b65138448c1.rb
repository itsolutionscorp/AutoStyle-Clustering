module Raindrops
	FACTORS = [[3, 'Pling'], [5, 'Plang'], [7, 'Plong']]
	
	def self.convert(number)
		result = FACTORS.each_with_object('') { |f, s| s.concat(f[1]) if (number % f[0] == 0) }
		result == '' ? number.to_s : result
	end
end

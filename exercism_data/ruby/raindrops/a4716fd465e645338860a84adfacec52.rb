class Raindrops

	def self.convert(num)
		rain = ""
		rain << "Pling" if num % 3 == 0
		rain << "Plang" if num % 5 == 0
		rain << "Plong" if num % 7 == 0
		rain << "#{num}" if rain.empty?
		return rain
	end


end

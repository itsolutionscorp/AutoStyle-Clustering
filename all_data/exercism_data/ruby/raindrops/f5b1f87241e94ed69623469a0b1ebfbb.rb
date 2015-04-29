class Raindrops

	def convert(num)
		rain_noises = ""
		rain_noises << "Pling" if num % 3 == 0
		rain_noises << "Plang" if num % 5 == 0
		rain_noises << "Plong" if num % 7 == 0

		if rain_noises.empty?
			return num.to_s
		end
		resulf
	end
end

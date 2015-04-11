class Raindrops
	def self.convert(nb)
		str = ""
		if nb % 3 == 0
			str += 'Pling'
		end
		if nb % 5 == 0
			str += 'Plang'
		end
		if nb % 7 == 0
			str += 'Plong'
		end
		if str != ""
			return str
		end
		return nb.to_s
	end
end

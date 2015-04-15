class Raindrops
	def self.convert(i)
		s = ""
		s << "Pling" if i%3 == 0
		s << "Plang" if i%5 == 0
		s << "Plong" if i%7 == 0
		s.empty? ? i.to_s : s
	end
end

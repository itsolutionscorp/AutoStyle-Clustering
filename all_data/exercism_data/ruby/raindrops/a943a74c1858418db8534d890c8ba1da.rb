class Raindrops
	def self.convert n
		out = ''
		out += 'Pling' if n % 3 == 0
		out += 'Plang' if n % 5 == 0
		out += 'Plong' if n % 7 == 0
		return out == '' ? n.to_s : out
	end
end

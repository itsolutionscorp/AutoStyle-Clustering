class Raindrops
	def self.convert(n)
		output = String.new
		output << 'Pling' if n%3==0
		output << 'Plang' if n%5==0
		output << 'Plong' if n%7==0
		output << n.to_s if n%5>0 and n%3>0 and n%7>0
		return output
	end
end

class Raindrops
	def self.convert(x)
		if x % 3 == 0 then y="Pling" end
		if x % 5 == 0 then y="#{y}Plang" end
		if x % 7 == 0 then y="#{y}Plong" end
		if y==nil then y=x.to_s end
		return y
	end
end

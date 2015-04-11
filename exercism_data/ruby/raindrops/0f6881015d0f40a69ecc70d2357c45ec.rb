class Raindrops
	def self.convert(dig)
		depends = {3 => "Pling", 5 => "Plang", 7 => "Plong"}
		depends.select{| key, item | dig % key == 0} \
		.values \
		.join \
		.sub(/^$/, dig.to_s)
	end
end

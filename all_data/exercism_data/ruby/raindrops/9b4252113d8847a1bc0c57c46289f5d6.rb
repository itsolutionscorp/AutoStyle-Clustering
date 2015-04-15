class Raindrops
	def self.convert(int)
		response = String.new
		map = {3 => 'Pling', 5 => 'Plang', 7 => 'Plong'}
		map.each_pair do |k,v|
			if int % k == 0
				response << v
			end
		end
		if response.length == 0
			response << int.to_s
		end
		return response
	end
end

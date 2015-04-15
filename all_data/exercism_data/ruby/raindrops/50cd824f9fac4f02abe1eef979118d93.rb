class Raindrops
	def self.convert(n)
		response = ""

		response << 'Pling' if n % 3 == 0
		response << 'Plang' if n % 5 == 0
		response << 'Plong' if n % 7 == 0
		response << n.to_s unless (n % 3 == 0) || (n % 5 == 0) ||  (n % 7 == 0)

		return response
				
	end

end

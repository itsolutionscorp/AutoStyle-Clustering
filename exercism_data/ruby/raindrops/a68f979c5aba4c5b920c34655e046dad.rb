class Raindrops

	CONVERSION_HASH = { "3" => "Pling", "5" => "Plang", "7" => "Plong" }

	def self.convert number
		result = ""
		CONVERSION_HASH.each do |k, v|
			result << v if number % k.to_i == 0
		end
		result = number.to_s if result.empty?
		result
	end
end

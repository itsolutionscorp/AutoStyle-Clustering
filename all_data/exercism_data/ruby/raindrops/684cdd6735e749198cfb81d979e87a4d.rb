class Raindrops
	def self.convert(number)
		message = ""
		message_rules = { 3 => "Pling", 5 => "Plang", 7 => "Plong"}
		message_rules.each do |key, value| 
			if number % key == 0
				message += value
			end
		end
		if message == ""
			message += number.to_s
		end
		message
	end
end

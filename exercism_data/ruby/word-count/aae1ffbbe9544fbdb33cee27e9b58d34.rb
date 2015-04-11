class Phrase
	def initialize(message)
		@message = message.downcase.gsub(",", " ").gsub(/[^a-z0-9 ']/,"")
	end

	def word_count
		temp = {}
		for item in @message.split(" ")
			temp[item] = temp[item].to_i + 1
		end
		temp
	end
end

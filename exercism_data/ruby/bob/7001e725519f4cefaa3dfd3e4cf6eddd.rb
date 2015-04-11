class Bob 
  def hey(sentence)
		if yelling?(sentence)
			"Woah, chill out!"
		elsif asking?(sentence)
			"Sure."
		elsif silent?(sentence)
			"Fine. Be that way!"
		else
			"Whatever."
		end
	end

	private 

	def yelling?(sentence)
		sentence.match(/[a-zA-Z]/) && sentence == sentence.upcase 
	end

	def asking?(sentence)
		sentence[-1] == "?"
	end

	def silent?(sentence)
		sentence.strip.empty?
	end
end

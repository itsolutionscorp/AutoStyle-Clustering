class Bob

	def hey string

		if question? string
			return "Sure."
		end

		if (yelling? string) && (!question? string) && (!string.empty?)
			return "Woah, chill out!"
		end

		if (statement? string) && (!yelling? string)
			return "Whatever."
		end

		if string == ""
			return "Fine. Be that way!"
		end
	end

	private

	def question? string
		return true if string.match(/\?/)
	end

	def statement? string
		return true if !string.match(/\?/)
	end

	def yelling? string
		return true if (!string.match(/[a-z]/)) && (string.match(/[A-Z]/))
	end

end



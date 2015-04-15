module Teenager
  def is_uppercase(str)
    str.upcase == str
  end

  def is_question(str)
  	str.end_with?("?")
  end

  def is_silence(str)
  	str.strip.empty?
  end

end

class Bob
	include Teenager

	def hey(str)
		if(is_silence(str))
			return "Fine. Be that way!"
		end

		if(is_uppercase(str))
			return "Woah, chill out!"
		end

		if(is_question(str))
			return "Sure."
		end

		return "Whatever."
	end
end

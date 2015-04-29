class Bob
	def hey(str)
    return "Fine. Be that way." if silent? str
		return "Sure." if question? str
		return "Woah, chill out!" if yelling? str
		"Whatever."
	end

  private
    def silent?(str)
      str.nil? || str.empty?
    end

    def question?(str)
      str.end_with?("?")
    end

    def yelling?(str)
      str.upcase == str
    end
end

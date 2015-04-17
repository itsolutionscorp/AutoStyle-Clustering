class String
  def ends_with?(char)
    self[-1,1] == char
  end
  def alpha?
    self.match(/[A-Za-z]/)
  end
end

class Bob
	def hey(msg)
		case
		when empty?
			"Fine. Be that way!"
		when msg == msg.upcase && msg.alpha?
			"Woah, chill out!"
		when msg.ends_with?("?")
			"Sure."
		when !msg.alpha?
			"Whatever."
		else
			"Whatever."
		end
	end
end
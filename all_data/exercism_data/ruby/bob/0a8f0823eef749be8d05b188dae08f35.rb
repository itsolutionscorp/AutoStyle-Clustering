class Bob

  def hey(msg)
		case
		when silence?(msg); "Fine. Be that way."
		when yelling?(msg); "Woah, chill out!"
		when asking?(msg); "Sure."
		else "Whatever."
		end
  end

  private

  def silence?(msg)
    msg.nil? or msg.empty?
  end

  def yelling?(msg)
    !msg.match(/[a-z]/)
  end

  def asking?(msg)
    msg.end_with?("?")
  end

end

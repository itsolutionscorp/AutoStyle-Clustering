class Bob
  def hey(text)
    return "Fine. Be that way!" if pause? text
    return "Woah, chill out!"   if shouting? text
    return "Sure."              if question? text
    return "Whatever."
  end

  private
	  def pause?(text)
	    text.strip.empty?
	  end

	  def shouting?(text)
	    text !~ /[a-z]+/ && text =~ /[A-Z]+/
	  end

	  def question?(text)
	    text.end_with?("?")
	  end
end

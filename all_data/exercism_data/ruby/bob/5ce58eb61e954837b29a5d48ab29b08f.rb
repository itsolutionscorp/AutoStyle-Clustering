class Bob
  def hey text
    if(text.strip.empty?)
	  "Fine. Be that way!"
	elsif(text == text.upcase && text.match(/[a-zA-Z]+/))
	  "Woah, chill out!"
	elsif(text.end_with?("?"))
      "Sure."
	else
	  "Whatever."
	end
  end
end

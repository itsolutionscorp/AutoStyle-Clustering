class Bob
  def hey speak
  	if speak.strip.length == 0
  	  "Fine. Be that way!"
  	elsif speak == speak.upcase && speak.match(/[a-zA-Z]/)
      "Woah, chill out!"
  	elsif speak[-1,1] == "?"
  	  "Sure."
  	else
  	  "Whatever."
  	end
  end
end

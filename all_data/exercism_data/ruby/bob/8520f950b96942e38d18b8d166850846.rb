class Bob
  def hey(inquiry)
  	if inquiry.strip == ''
  	  "Fine. Be that way!"
    elsif inquiry.match(%r{[A-Z]}) && inquiry.upcase == inquiry
      "Woah, chill out!"
    
    elsif inquiry.match(%r{\?\z})
      "Sure."
    else
      "Whatever."
		end
  end
end

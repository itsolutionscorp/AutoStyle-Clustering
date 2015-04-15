class Bob
  def hey(input)
  	if input =~ /\A\s*\z/
  	  'Fine. Be that way!'
  	elsif input.upcase == input && input =~ /[A-Z]/
  	  'Whoa, chill out!'
  	elsif input.end_with? '?'
  	  'Sure.'
  	else
  	  'Whatever.'
  	end
  end
end

class Bob

  def hey(message)
    capital   = (message =~ /[a-z]/).nil? && !(message =~ /[A-Z]/).nil?
    question  = (message[-1,1] == '?')
    blank     = message.strip.empty?

    if capital 
      'Woah, chill out!'
    elsif question 
      'Sure.'
    elsif blank
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end

end

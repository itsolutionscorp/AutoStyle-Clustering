class Bob
  def hey(phrase)
    if phrase.lstrip == ""
      'Fine. Be that way!'
    elsif (phrase == phrase.upcase) && has_alpha?(phrase)
      'Woah, chill out!'
    elsif  phrase.end_with?('?')
        'Sure.'
    else      
     'Whatever.'
   end
  end
  
  private
  def has_alpha?(string)
    return string =~ /[A-Za-z]+/
  end
end

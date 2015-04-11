class Bob
  def initialize
  end
  def hey(phrase)
    puts phrase
    if phrase.lstrip == ""
      'Fine. Be that way!'
    elsif (phrase == phrase.upcase) && (phrase =~ /[A-Za-z]+/)
      'Woah, chill out!'
    elsif  phrase.end_with?('?')
        'Sure.'
    else      
     'Whatever.'
   end
  end
end

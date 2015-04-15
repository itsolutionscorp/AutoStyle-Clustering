class Bob
  def hey(word)
    case
    when word.strip.empty?
      'Fine. Be that way!'
    when word.upcase == word
      'Woah, chill out!'
    when word.end_with?('?')
      'Sure.'
    else
      'Whatever.'
    end
  end
end
 

 

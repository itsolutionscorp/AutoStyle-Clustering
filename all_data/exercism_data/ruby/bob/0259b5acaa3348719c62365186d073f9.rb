class Bob
  def hey sentence
    question = /[A-Z][a-z].*\?$/
    yell = /^[^[:lower:]]*$/ 
    nothing = /^\s*$/ 

    case sentence
      when question then 'Sure.'
      when nothing then 'Fine. Be that way!'
      when yell then  'Woah, chill out!'
      else
        'Whatever.'
    end
  end
end

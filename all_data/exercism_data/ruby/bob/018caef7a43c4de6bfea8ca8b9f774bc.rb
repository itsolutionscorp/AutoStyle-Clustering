class Bob
  def hey(input)
    case
     when yell?(input.strip)
      'Woah, chill out!'
    when address?(input.strip)
     'Fine. Be that way!'  
    when question?(input)
     'Sure.'
  
    else
     'Whatever.'
    end
  end
  def address?(s)
    s.empty?
  end
  def question?(s)
    s.end_with?('?')
  end
  def yell?(s)
    if(!s.empty?)  
     s.upcase == s
    end
  end
end

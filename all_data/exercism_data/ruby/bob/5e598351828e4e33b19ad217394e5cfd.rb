class Bob
  def hey(input)
    case
    when address?(input)
     'Fine. Be that way!'  
    when question?(input)
     puts 'Sure.'
    when yell?(input)
    'Woah, chill out!'
    
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
  s.upcase == s
  end
end

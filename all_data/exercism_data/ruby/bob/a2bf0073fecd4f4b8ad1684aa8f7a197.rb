class Bob
  def hey(query)
    case responder(query.strip)
    when :silent
      'Fine. Be that way!'
    when :shouting
      'Woah, chill out!'
    when :questioned
      'Sure.'
    else
      'Whatever.'
    end
  end
  def responder(value)
    case 
    when value.empty?
      :silent
    when value.upcase == value
      :shouting
    when value.end_with?('?')
      :questioned
    end
  end
end

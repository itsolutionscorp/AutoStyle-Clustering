class Bob

  def hey(rant)

    exchange = Communication.new

    if exchange.silent_treatment? rant
      'Fine. Be that way!'
    elsif exchange.yelling? rant
      'Woah, chill out!'
    elsif exchange.asking_something? rant
      'Sure.'
    else
      'Whatever.'
    end

  end

end

class Communication

  def silent_treatment?(string)
    string.to_s.strip == ''
  end

  def yelling?(string)
    string == string.upcase
  end

  def asking_something?(string)
    string[-1] == '?'
  end

end

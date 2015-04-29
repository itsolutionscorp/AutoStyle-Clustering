class Bob

  def hey(rant)

    exchange = Communication.new

    if exchange.silentTreatment rant
      'Fine. Be that way!'

    elsif exchange.yell rant
      'Woah, chill out!'

    elsif exchange.askingQuestion rant
      'Sure.'

    else
      'Whatever.'
    end

  end

end

class Communication

  def silentTreatment(string)
    string.to_s.strip == ''
  end

  def yell(string)
    string == string.upcase
  end

  def askingQuestion(string)
    string[-1, 1] == '?'
  end

end

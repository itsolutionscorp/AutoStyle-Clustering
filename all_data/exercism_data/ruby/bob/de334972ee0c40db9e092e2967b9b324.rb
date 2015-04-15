class Bob
  def hey message
    case message
    when silence        then 'Fine. Be that way!'
    when message.upcase then 'Woah, chill out!'
    when question       then 'Sure.'
    else
      'Whatever.'
    end
  end

  private
  def silence
    /^\s*$/
  end

  def question
    /\?$/
  end
end

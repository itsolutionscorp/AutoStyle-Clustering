class Bob

  def hey message

    empty = ->(message) { message == '' }
    shouting = ->(message) { message == message.upcase }
    question = ->(message) { message.end_with?('?') }

    case message.to_s
    when empty
      'Fine. Be that way!'
    when shouting
      'Woah, chill out!'
    when question
      'Sure.'
    else
      "Whatever."
    end

  end

end

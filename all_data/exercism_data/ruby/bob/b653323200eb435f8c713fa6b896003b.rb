class Bob

  def hey message

    case message.to_s
    when told[:empty]
      'Fine. Be that way!'
    when told[:shouting]
      'Woah, chill out!'
    when told[:question]
      'Sure.'
    else
      'Whatever.'
    end

  end

  private
  def told
    @told ||= 
      {
      empty:    ->(message) { message == '' },
      shouting: ->(message) { message == message.upcase },
      question: ->(message) { message.end_with?('?') }
      }
  end
end

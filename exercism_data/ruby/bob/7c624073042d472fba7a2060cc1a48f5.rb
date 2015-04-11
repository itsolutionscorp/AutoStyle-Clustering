class Bob
  
  attr_reader :message

  def hey(message)
    @message = message

    respond_to_message
  end

  def respond_to_message
    case 
      when says_nothing?
        'Fine. Be that way!'
      when is_yelling? && contains_letter?
        'Woah, chill out!'
      when asks_question?
        'Sure.'
      else
        'Whatever.'
    end
  end

  def says_nothing?
    message.strip.empty?
  end

  def is_yelling?
    message == message.upcase
  end

  def contains_letter?
    message != message.gsub(/[A-Za-z]/, '')
  end

  def asks_question?
    message.end_with?('?')
  end

end

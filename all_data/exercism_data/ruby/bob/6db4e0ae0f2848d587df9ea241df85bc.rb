class Bob
  attr_reader :message

  def hey(message)
    @message = message

    mute_response || shout_response || question_response || whatever_response
  end
  
  private

  def is_mute?
    message.strip == ''
  end
  
  def mute_response
    'Fine. Be that way!' if is_mute?
  end

  def is_shout?
    message.upcase == message
  end

  def shout_response
    'Woah, chill out!' if is_shout?
  end

  def is_question?
    message.end_with?('?')
  end

  def question_response
    'Sure.' if is_question?
  end

  def whatever_response
    'Whatever.'
  end
end

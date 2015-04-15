class Bob
  def hey(message)
    case message
    when is_question then react_to_question
    when is_yelling then react_to_yelling
    when is_silence then react_to_silence
    else default_reaction
    end
  end

  private

  def is_question
    /.+\?\z/
  end

  def is_yelling
    /\A[^a-z]+\z/
  end

  def is_silence
    ->(message) { message == '' or message == nil }
  end

  def default_reaction
    'Whatever.'
  end

  def react_to_question
    'Sure.'
  end

  def react_to_yelling
    'Woah, chill out!'
  end

  def react_to_silence
    'Fine. Be that way.'
  end
end

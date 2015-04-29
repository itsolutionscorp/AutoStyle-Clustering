class Bob
  CAPS_REGEX = /\A[^a-z]+\z/
  QUESTION_REGEX = /\?\z/
  SILENCE_REGEX = /\A\s*\z/

  def hey(message)
    case message
      when SILENCE_REGEX
        'Fine. Be that way!'
      when CAPS_REGEX
        'Woah, chill out!'
      when QUESTION_REGEX
        'Sure.'
      else
        'Whatever.'
    end
  end
end

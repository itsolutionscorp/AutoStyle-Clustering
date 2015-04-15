class Bob
  SILENCE_RESPONSE = 'Fine. Be that way!'
  QUESTION_RESPONSE = 'Sure.'
  GENERIC_RESPONSE = 'Whatever.'
  SHOUT_RESPONSE = 'Woah, chill out!'

  def hey(command)
    if shout?(command)
      SHOUT_RESPONSE
    elsif question?(command)
      QUESTION_RESPONSE
    elsif silence?(command)
      SILENCE_RESPONSE
    else
      GENERIC_RESPONSE
    end
  end

  private

  def shout?(command)
    command.match(/[A-Z]/) && command.upcase == command
  end

  def question?(command)
    command.match(/\?\Z/)
  end

  def silence?(command)
    command.strip.empty?
  end
end

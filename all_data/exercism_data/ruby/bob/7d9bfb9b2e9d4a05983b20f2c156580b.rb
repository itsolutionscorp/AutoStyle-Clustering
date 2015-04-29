class Bob

  MIXED_SIGNALS_REGEX = /^[A-Z ]+$/
  SCREAMING_RETHORICAL_QUESTION_REGEX = /^[A-Z ]+\?$/

  def hey(message)
    case(message.strip[-1])
    when "!"
      respond_to_serious_talk(message)
    when "?"
      respond_to_question(message)
    when nil
      respond_to_silent_treatment
    else
      respond_to_statement(message)
    end
  end

  private

  def respond_to_serious_talk(message)
    if(message.upcase == message)
      "Woah, chill out!"
    else
      dont_care
    end
  end

  def respond_to_question(message)
    if(message =~ SCREAMING_RETHORICAL_QUESTION_REGEX)
      "Woah, chill out!"
    else
      "Sure."
    end
  end

  def respond_to_statement(message)
    if(message =~ MIXED_SIGNALS_REGEX)
      "Woah, chill out!"
    else
      dont_care
    end
  end

  def respond_to_silent_treatment
    "Fine. Be that way!"
  end

  def dont_care
    "Whatever."
  end
end

class Bob
  def hey phrase
    if quiet? phrase
      respond_to_silence
    elsif shouting?(phrase) || phrase_includes_numbers?(phrase) || shouting_without_question_mark?(phrase)
      respond_to_shout
    elsif question? phrase
      respond_to_question
    elsif forcefully? phrase
      stating_something
    else
      stating_something
    end
  end

  private

  def shouting? phrase
    unless quiet? phrase
      phrase.upcase == phrase
    end
  end

  def question? phrase
    phrase.end_with? '?'
  end

  def forcefully? phrase
    phrase.end_with? '!'
  end

  def phrase_includes_numbers? phrase
    phrase.match /\d/
  end

  def shouting_without_question_mark? phrase
    shouting?(phrase) && forcefully?(phrase)
  end

  def quiet? phrase
    phrase.nil? || phrase.empty?
  end

  def stating_something
    "Whatever."
  end

  def respond_to_question
    "Sure."
  end

  def respond_to_shout
    'Woah, chill out!'
  end

  def respond_to_silence
    'Fine. Be that way.'
  end
end

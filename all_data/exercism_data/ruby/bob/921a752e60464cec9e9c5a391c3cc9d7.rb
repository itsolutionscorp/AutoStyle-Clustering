class Bob
  YELL_RESPONSE     = 'Woah, chill out!'
  QUESTION_RESPONSE = 'Sure.'
  SILENT_RESPONSE   = 'Fine. Be that way!'
  DEFAULT_RESPONSE  = 'Whatever.'

  def hey communication
    @communication = communication
    case
    when shouting? then YELL_RESPONSE
    when ask_question? then QUESTION_RESPONSE
    when silent? then SILENT_RESPONSE
    else DEFAULT_RESPONSE
    end
  end

  private

  def shouting?
    all_alphabetic_in_uppercase?
  end

  def all_alphabetic_in_uppercase?
    any_alphabetic_chars? && nothing_in_lowercase?
  end

  def ask_question?
    @communication.match(match_question)
  end

  def silent?
    @communication.match(match_any_non_whitespace).nil?
  end

  def any_alphabetic_chars?
    @communication.match(match_alphabetic)
  end

  def nothing_in_lowercase?
    @communication.match(match_lowercase).nil?
  end

  def match_question
    /[?]\z/
  end

  def match_any_non_whitespace
    /\S/
  end

  def match_lowercase
    /[[:lower:]]/
  end

  def match_alphabetic
    /[[:alpha:]]/
  end
end

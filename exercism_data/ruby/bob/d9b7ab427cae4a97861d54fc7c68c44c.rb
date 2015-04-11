class CommunicationRecognition
  attr_accessor :communication

  def ask_question?
    communication.end_with? '?'
  end

  def shouting?
    any_alphabetic_chars? && nothing_in_lowercase?
  end

  def silent?
    communication.strip.empty?
  end

  private

  def any_alphabetic_chars?
    communication.match(match_alphabetic)
  end

  def nothing_in_lowercase?
    communication.match(match_lowercase).nil?
  end

  def match_lowercase
    /[[:lower:]]/
  end

  def match_alphabetic
    /[[:alpha:]]/
  end
end

class Bob
  attr_reader :classify

  def initialize classify = CommunicationRecognition.new
    @classify = classify
  end

  YELL_RESPONSE     = 'Woah, chill out!'
  QUESTION_RESPONSE = 'Sure.'
  SILENT_RESPONSE   = 'Fine. Be that way!'
  DEFAULT_RESPONSE  = 'Whatever.'

  def hey communication
    classify.communication = communication
    case
    when classify.shouting? then YELL_RESPONSE
    when classify.ask_question? then QUESTION_RESPONSE
    when classify.silent? then SILENT_RESPONSE
    else DEFAULT_RESPONSE
    end
  end
end

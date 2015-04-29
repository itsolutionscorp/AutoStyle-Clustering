class Bob
  def hey(phrase)
    response = 'Sure.' if PhraseType.is_question?(phrase)
    response = 'Woah, chill out!' if PhraseType.is_yelling?(phrase)
    response = 'Fine. Be that way!' if PhraseType.is_silence?(phrase)
    response = 'Whatever.' if PhraseType.unrecognized_type?(response)
    response
  end
end

class PhraseType
  def self.is_question?(phrase)
    true if phrase.end_with?('?')
  end

  def self.is_yelling?(phrase)
    true if phrase.upcase == phrase && (/[a-zA-Z]/).match(phrase)
  end

  def self.is_silence?(phrase)
    true if phrase.split.length == 0
  end

  def self.unrecognized_type?(response)
    true if response == nil
  end
end

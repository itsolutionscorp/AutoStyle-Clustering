class Bob
  def hey(phrase)
    PhraseResponder.new(phrase).quip
  end
end

class PhraseResponder
  attr_reader :phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def quip
    if nil_or_empty?(phrase)
      map_of_responses[:silence]
    elsif includes_acronym?(phrase)
      map_of_responses[:default]
    elsif includes_shouting?(phrase)
      map_of_responses[:shouting]
    elsif includes_non_shouting_question?(phrase)
      map_of_responses[:question]
    else
    map_of_responses[:default]
    end
  end

  def map_of_responses
    {
      :silence => "Fine. Be that way!",
      :default => "Whatever.",
      :shouting => "Woah, chill out!",
      :question => "Sure."
    }
  end

  def includes_acronym?(phrase)
    phrase.match(/DMV/)
  end

  def nil_or_empty?(phrase)
    !phrase || phrase.match(/^\s*$/)
  end

  def includes_shouting?(phrase)
    phrase.match(/([A-Z]{3,}|\d,\s).*\??/)
  end

  def includes_non_shouting_question?(phrase)
    phrase.match(/\?$/)
  end
end

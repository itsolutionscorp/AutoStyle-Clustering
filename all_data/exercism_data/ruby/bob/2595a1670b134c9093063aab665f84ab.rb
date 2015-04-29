class Bob

  def hey(sentence)
    klass = klassify(sentence)
    klass.respond
  end

  private
  def klassify(sentence)
    is_yell = -> (s) { !s.strip.scan(/[A-Z]/).empty? && s.strip.scan(/[A-Z]/)  == s.strip.scan(/[a-zA-Z]/) }
    is_question = -> (s) { s.end_with?("?") }
    is_silence = -> (s) { s.strip.empty? }

    case sentence
    when is_yell
      yell
    when is_question
      question
    when is_silence
      silence
    else
      whatever
    end
  end

  def yell; Yell; end
  def question; Question; end
  def silence; Silence; end
  def whatever; Whatever; end
end

class Yell
  def self.respond
    "Woah, chill out!"
  end
end

class Question
  def self.respond
    "Sure."
  end
end

class Silence
  def self.respond
    "Fine. Be that way!"
  end
end

class Whatever
  def self.respond
    "Whatever."
  end
end

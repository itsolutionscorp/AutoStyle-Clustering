module LackadaisicalTeenager

  class NothingUtterance
    def self.accept?(message)
      message.empty?
    end

    def self.reply
      'Fine. Be that way.'
    end
  end

  class YellingUtterance
    def self.accept?(message)
      message.upcase == message
    end

    def self.reply
      "Woah, chill out!"
    end
  end

  class QuestionUtterance
    def self.accept?(message)
      message[-1] == '?'
    end

    def self.reply
      "Sure."
    end
  end

  class UnknownUtterance

    def self.accept?(message)
      true
    end

    def self.reply
      "Whatever."
    end
  end


end

module LackadaisicalTeenager

  UTTERANCES = [NothingUtterance, YellingUtterance, QuestionUtterance, UnknownUtterance]

  def utterances
    UTTERANCES
  end

  def respond(message)

    utterance = utterances.find { | u | u.accept?(message) }
    utterance.reply

  end

end

class Bob
  include LackadaisicalTeenager

  def hey(message)
    respond(message)
  end

end

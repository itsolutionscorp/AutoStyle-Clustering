module LackadaisicalTeenager

  class NothingParle
    def self.reply?(message)
      message.empty?
    end

    def self.reply
      'Fine. Be that way.'
    end
  end

  class YellingParle
    def self.reply?(message)
      message.upcase == message
    end

    def self.reply
      "Woah, chill out!"
    end
  end

  class QuestionParle
    def self.reply?(message)
      message[-1] == '?'
    end

    def self.reply
      "Sure."
    end
  end

  class UnknownParle

    def self.reply?(message)
      true
    end

    def self.reply
      "Whatever."
    end
  end


end

module LackadaisicalTeenager

  PARLE = [NothingParle, YellingParle, QuestionParle, UnknownParle]

  def respond(message)

    action = PARLE.find { | response | response.reply?(message) }
    action.reply

  end

end

class Bob
  include LackadaisicalTeenager

  def hey(message)
    respond(message)
  end

end

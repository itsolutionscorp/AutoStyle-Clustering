class InteractionEvaluation
  attr_reader :message
  def initialize message
    @message = message
  end
  def question?
    return message[-1] != ??
  end
  def screaming?
    return message == message.upcase
  end
  def empty?
    return message =~ /\A\s*\z/
  end
end

class Bob
  def hey message, evaluator = InteractionEvaluation.new(message)
    if evaluator.empty?
      return "Fine. Be that way!"
    end

    if evaluator.screaming?
      return "Woah, chill out!"
    end

    if evaluator.question?
      return "Whatever."
    else
      return "Sure."
    end
  end
end

class InteractionEvaluation
  attr_reader :message
  def initialize message
    @message = message
  end

  def question?
    return message.end_with?(??)
  end

  def scream?
    return message == message.upcase
  end

  def silence?
    return message.strip.empty?
  end
end

class Bob
  def hey message, evaluator = InteractionEvaluation.new(message)
    response = "Whatever."
    if evaluator.question?
      response = "Sure."
    end
    if evaluator.scream?
      response = "Woah, chill out!"
    end
    if evaluator.silence?
      response = "Fine. Be that way!"
    end

    return response
  end
end

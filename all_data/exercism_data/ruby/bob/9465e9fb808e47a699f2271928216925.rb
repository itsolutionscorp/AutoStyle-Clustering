class InteractionEvaluation
  attr_reader :message
  def initialize message
    @message = message
  end

  def question?
    message.end_with?("?")
  end

  def scream?
    message == message.upcase
  end

  def silence?
    message.strip.empty?
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

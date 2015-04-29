class InteractionEvaluation
  attr_reader :message
  def initialize message
    @message = message
  end
  def question?
    return message[-1] == ?? && !scream? && !silence?
  end
  def scream?
    return message == message.upcase && !silence?
  end
  def silence?
    return message.strip == ''
  end
end

class Bob
  def hey message, evaluator_class = InteractionEvaluation
    evaluator = evaluator_class.new(message)

    response = "Whatever."
    if evaluator.question?
      response = "Sure."
    elsif evaluator.scream?
      response = "Woah, chill out!"
    elsif evaluator.silence?
      response = "Fine. Be that way!"
    end
    return response
  end
end

class Bob
  RESPONSES = {
    woah:     "Woah, chill out!",
    sure:     "Sure.",
    whatever: "Whatever.",
    fine:     "Fine. Be that way!"
  }

  def initialize
    @evaluator = ConversationEvaluator.new(self)
  end

  def hey(words)
    @evaluator.evaluate(words)
  end

  def respond(response)
    RESPONSES[response]
  end
end

class ConversationEvaluator
  attr_reader :initiator

  def initialize(initiator)
    @initiator = initiator
  end

  def evaluate(words)
    if words.match /[A-Z]/ and words == words.upcase
      initiator.respond(:woah)
    elsif words.end_with?("?")
      initiator.respond(:sure)
    elsif words.strip.empty?
      initiator.respond(:fine)
    else
      initiator.respond(:whatever)
    end
  end
end

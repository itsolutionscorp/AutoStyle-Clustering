class Bob
  def hey(words)
    respond generate_response_evaluator(words)
  end

  class ResponseEvaluator < String
    def silence?
      empty?
    end

    def question?
      end_with? '?'
    end

    def shouting?
      upcase == self
    end
  end

  private

  def generate_response_evaluator(words)
    ResponseEvaluator.new(words.to_s)
  end

  def respond(response_evaluator)
    case
    when response_evaluator.silence? then 'Fine. Be that way.'
    when response_evaluator.question? then 'Sure.'
    when response_evaluator.shouting? then 'Woah, chill out!'
    else 'Whatever.'
    end
  end
end

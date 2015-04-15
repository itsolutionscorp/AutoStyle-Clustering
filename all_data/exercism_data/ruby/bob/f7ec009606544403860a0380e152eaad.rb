class Bob
  def hey(words)
    respond generate_input_evaluator(words)
  end

  class InputEvaluator < String
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

  def generate_input_evaluator(words)
    InputEvaluator.new(words.to_s)
  end

  def respond(input_evaluator)
    case
    when input_evaluator.silence? then 'Fine. Be that way.'
    when input_evaluator.question? then 'Sure.'
    when input_evaluator.shouting? then 'Woah, chill out!'
    else 'Whatever.'
    end
  end
end

class Bob
  def generate_response_evaluator(words)
    ResponseEvaluator.new(words)
  end

  def hey(words)
    response_evaluator = generate_response_evaluator(words)

    case
    when response_evaluator.silence? then 'Fine. Be that way.'
    when response_evaluator.question? then 'Sure.'
    when response_evaluator.shouting? then 'Woah, chill out!'
    else 'Whatever.'
    end
  end

  class ResponseEvaluator
    attr_reader :words

    def initialize(words)
      @words = words
    end

    def silence?
      words.to_s.empty?
    end

    def question?
      words.end_with? '?'
    end

    def shouting?
      words.upcase == words
    end
  end
end

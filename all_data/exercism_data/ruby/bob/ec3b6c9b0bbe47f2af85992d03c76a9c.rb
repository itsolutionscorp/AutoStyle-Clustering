class Bob

  class Expression
    def initialize(expression)
      @expression = expression
    end

    def shouting?
      !silence? && @expression == @expression.upcase
    end

    def question?
      @expression =~ /\?\Z/
    end

    def silence?
      @expression.strip == ''
    end
  end


  def hey(words)
    expression = Expression.new(words)
    response_for(expression) || default_response
  end

  def default_response
    "Whatever."
  end

  def response_for(expression)
    if expression.shouting?
      'Woah, chill out!'
    elsif expression.question?
      'Sure.'
    elsif expression.silence?
      'Fine. Be that way!' 
    end
  end

end

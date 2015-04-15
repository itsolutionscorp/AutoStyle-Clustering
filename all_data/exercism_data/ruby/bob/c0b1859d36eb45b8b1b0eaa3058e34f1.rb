class Bob 
  
  def initialize
    @responses = {'question' => 'Sure.', 'yell' => 'Woah, chill out!', 'nothing' => 'Fine. Be that way!'}
    @responses.default = 'Whatever.'
  end

  def hey (expression)
    expression = expression.strip
    
    if is_yelling(expression)
      return @responses['yell']
    end
    
    if is_question(expression)
      return @responses['question']
    end
    
    if is_nothing (expression)
      return @responses['nothing']
    end
    
    return @responses['']
  end
  
  def is_question (expression)
    return expression[-1] == '?'
  end
  
  def is_yelling (expression)
    return ! expression.upcase! && /[A-Z]+/.match(expression)
  end
  
  def is_nothing (expression)
    return expression == ''
  end
end

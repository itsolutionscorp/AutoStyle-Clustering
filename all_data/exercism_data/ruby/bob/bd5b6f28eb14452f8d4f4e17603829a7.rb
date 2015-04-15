class Bob 
  
  def initialize
    @responses = {'question' => 'Sure.', 'yell' => 'Woah, chill out!', 'nothing' => 'Fine. Be that way!'}
    @responses.default = 'Whatever.'
  end

  def hey (expression)
    expression = expression.strip
    
    if is_yelling(expression)
      @responses['yell']
    elsif is_question(expression)
      @responses['question']
    elsif is_nothing(expression)
      @responses['nothing']
    else
      @responses['']
    end
  end
  
  def is_question (expression)
    expression[-1] == '?'
  end
  
  def is_yelling (expression)
    !expression.upcase! && /[A-Z]+/.match(expression)
  end
  
  def is_nothing (expression)
    expression == ''
  end
end

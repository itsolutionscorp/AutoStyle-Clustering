class StringType
  def initialize(expression)
    @expression = expression.strip
  end
  
  def is_question?
    @expression[-1] == '?'
  end
  
  def is_yelling?
    !(@expression.upcase!) && /[A-Z]+/.match(@expression)
  end
  
  def is_nothing?
    @expression == ''
  end
end 

class Bob 
  
  def initialize
    @responses = {'question' => 'Sure.', 'yell' => 'Woah, chill out!', 'nothing' => 'Fine. Be that way!'}
    @responses.default = 'Whatever.'
  end

  def hey (expression)
    type = StringType.new(expression)
    
    if type.is_yelling?
      @responses['yell']
    elsif type.is_question?
      @responses['question']
    elsif type.is_nothing?
      @responses['nothing']
    else
      @responses['']
    end
  end
end

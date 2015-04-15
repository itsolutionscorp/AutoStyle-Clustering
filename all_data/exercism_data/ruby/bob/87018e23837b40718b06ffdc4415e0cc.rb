class Bob

  def hey(input)
    input = Input.new(input)
    answer = 'Whatever.'

    if input.silence?
      answer = 'Fine. Be that way!'
    end
    
    if input.yell?
      answer = 'Woah, chill out!'
    end

    if input.question?
      answer = 'Sure.'
    end

    answer
  end

end

class Input
  attr_accessor :input

  def initialize(input)
    @input = input.to_s
  end
  
  def question?
    @input.end_with?('?') and not yell?
  end

  def yell?
    @input.upcase == input and not silence?
  end

  def silence?
    @input == ''
  end

end

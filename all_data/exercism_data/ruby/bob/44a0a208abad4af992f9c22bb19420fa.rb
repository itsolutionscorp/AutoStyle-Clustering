class InputAnalyzer
  
  attr_reader :input
  
  def initialize(input)
    @input = input
  end
  
  def silence?
    input.nil? || input.strip.empty?
  end
  
  def yelling?
    input == input.upcase
  end
  
  def question?
    input.end_with?("?")
  end
  
end


class Bob
  
  def hey(user_input)
    input = InputAnalyzer.new(user_input)
    
    case
    when input.silence?
      'Fine. Be that way!'
    when input.yelling?
      'Woah, chill out!'
    when input.question?
      'Sure.'
    else
      'Whatever.'
    end
  end
  
end

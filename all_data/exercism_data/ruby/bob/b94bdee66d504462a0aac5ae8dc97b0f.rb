class Bob
  def hey(input)    
    case Sentence.new(input)
    when is(:silence?)
      'Fine. Be that way!'
    when is(:shouting?)
      'Woah, chill out!'
    when is(:question?)
      'Sure.'
    else
      'Whatever.'
    end
  end

  private
  def is(method_name)
    method_name.to_proc
  end
end

class Sentence
  def initialize(input)
    @input = input.to_s
  end

  def silence?
    @input.strip.empty?
  end

  def shouting?
    @input == @input.upcase
  end

  def question?
    @input.end_with?("?")
  end  
end

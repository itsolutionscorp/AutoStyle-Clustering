class Bob
  def hey(input)
    case Sentence.new(input)
    when :silence?.to_proc
      'Fine. Be that way!'
    when :shouting?.to_proc
      'Woah, chill out!'
    when :question?.to_proc
      'Sure.'
    else
      'Whatever.'
    end
  end  
end

class Sentence
  def initialize(input)
    @input = input.to_s
  end
  def silence?
    @input !~ /\S/
  end
  def shouting?
    @input == @input.upcase
  end
  def question?
    @input.end_with?("?")
  end  
end

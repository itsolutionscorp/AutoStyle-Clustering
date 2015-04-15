class Sentence

  attr_reader :sentence #I had some help with this line of code

  def initialize(response)
    @sentence = response.to_s.strip
  end

  def question?
    sentence.end_with?('?')
  end

  def yell?
    sentence =~ /[A-Z]/ && sentence.upcase == sentence #I had some help with this line of code!
  end

  def ingnore?
    sentence.empty?
  end
  
end

class Bob

  def hey(response)
    answers Sentence.new(response)
  end

  def answers(sentence)
    case
    when sentence.ingnore?
      'Fine. Be that way!'
    when sentence.yell?
      'Woah, chill out!'
    when sentence.question?
      'Sure.'
    else
      'Whatever.'
    end
  end

end

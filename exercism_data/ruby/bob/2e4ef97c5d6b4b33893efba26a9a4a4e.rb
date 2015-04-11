class Bob

  def hey(words)
    sentence = Sentence.new(words)
    case 
    when sentence.yelling?
      'Woah, chill out!'
    when sentence.question?
      'Sure.'
    when sentence.quiet?
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end
end

class Sentence
  
  def initialize(words)
    @words = words
  end

  def yelling?
    @words.match(/[a-zA-Z]/) && @words.upcase.eql?(@words) 
  end

  def question?
    @words.end_with?('?') 
  end

  def quiet?
    @words.strip.empty?
  end

end

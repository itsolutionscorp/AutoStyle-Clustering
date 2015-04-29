class Bob
  def hey(text)
    sentence = Sentence.new(text)
    case
    when sentence.silence?  then 'Fine. Be that way!'
    when sentence.yelling?  then 'Woah, chill out!'
    when sentence.question? then 'Sure.'
    else 'Whatever.'
    end
  end
end

class Sentence
  def initialize(text)
    @text = text
  end

  def question?
    @text.end_with?('?') && !yelling?
  end

  def yelling?
    @text == @text.upcase && !silence?
  end

  def silence?
    @text.strip.empty?
  end
end

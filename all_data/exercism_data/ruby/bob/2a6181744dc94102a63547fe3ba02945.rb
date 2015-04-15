class Sentence

  def initialize(sentence)
    @text = sentence.strip
  end

  def silence?
    @text.empty?
  end

  def shout?
    @text == @text.upcase && !@text.index(/[[:alpha:]]/).nil?
  end

  def question?
    @text.end_with?('?')
  end
  
end

class Bob

  def hey(text)
    sentence = Sentence.new(text)
    if sentence.silence?
      'Fine. Be that way!'
    elsif sentence.shout?
      'Woah, chill out!'
    elsif sentence.question?
      'Sure.'
    else
      'Whatever.'
    end
  end

end

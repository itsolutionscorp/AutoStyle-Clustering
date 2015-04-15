class Bob
  def hey(text)
    respond_to(Sentence.new(text))
  end

  private

  def respond_to(sentence)
    if sentence.silence?
      'Fine. Be that way.'
    elsif sentence.yelling?
      'Woah, chill out!'
    elsif sentence.question?
      'Sure.'
    else
      'Whatever.'
    end
  end
end

class Sentence
  attr_reader :text

  def initialize(text)
    @text = text.to_s
  end

  def silence?
    text.empty?
  end

  def yelling?
    text.upcase == text
  end

  def question?
    text.end_with?('?')
  end
end

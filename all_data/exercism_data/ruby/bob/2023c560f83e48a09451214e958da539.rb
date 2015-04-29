class Bob

  def hey(addressing_speech)
    speech = Speech.new(addressing_speech)
    if speech.question?
      'Sure.'
    elsif speech.yell?
      'Woah, chill out!'
    elsif speech.empty?
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end

end

class Speech

  attr_reader :text

  def initialize(text)
    @text = text
  end

  def question?
    !yell? && text[-1] == '?'
  end

  def yell?
    contains_letters? && text.upcase == text
  end

  def empty?
    text.strip.empty?
  end

  private

  def contains_letters?
    /[a-zA-Z]/ =~ text
  end

end

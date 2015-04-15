class Bob
  def hey phrase
    phrase = Phrase.new phrase
    if phrase.shouting?
      'Woah, chill out!'
    elsif phrase.asking_a_question?
      'Sure.'
    elsif phrase.silence?
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end
end

class Phrase
  attr_reader :text

  def initialize text
    @text = text
  end

  def shouting?
    text == text.upcase && text != text.downcase
  end

  def asking_a_question?
    text.end_with? '?'
  end

  def silence?
    text.strip == ''
  end
end

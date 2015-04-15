class Phrase
  def initialize(text)
    @text = text
  end

  def silence?
    @text.to_s.strip.empty?
  end

  def shouting?
    @text =~ /\A[^a-z]+\z/
  end

  def question?
    @text.end_with?('?')
  end
end

class Bob
  def hey(text)
    phrase = Phrase.new(text)

    if phrase.silence?
      'Fine. Be that way!'
    elsif phrase.shouting?
      'Woah, chill out!'
    elsif phrase.question?
      'Sure.'
    else
      'Whatever.'
    end
  end
end

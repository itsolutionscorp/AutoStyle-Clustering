class Bob
  def hey phrase
    phrase = Phrase.new phrase if phrase.is_a? String

    case phrase.type
    when :shouting          then 'Woah, chill out!'
    when :asking_a_question then 'Sure.'
    when :silence           then 'Fine. Be that way!'
    else                         'Whatever.'
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

  def type
    if    shouting?          then :shouting
    elsif asking_a_question? then :asking_a_question
    elsif silence?           then :silence
    else                          :other
    end
  end
end

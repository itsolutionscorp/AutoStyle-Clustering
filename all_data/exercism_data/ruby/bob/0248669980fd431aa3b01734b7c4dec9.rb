class Bob
  def hey string
    phrase = Phrase.new string

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

class Phrase
  attr_reader :string
  def initialize string
    @string = string
  end

  def silence?
    string.nil? || string.strip.empty?
  end

  def shouting?
    string == string.upcase
  end

  def question?
    string.end_with? '?'
  end
end

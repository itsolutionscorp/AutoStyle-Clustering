class Bob
  def hey string
    phrase = Phrase.new string

    if phrase.quiet?
      'Fine. Be that way.'
    elsif phrase.shouting?
      'Woah, chill out!'
    elsif phrase.question?
      "Sure."
    else
      "Whatever."
    end
  end
end

class Phrase
  def initialize string
    @string = string
  end

  def quiet?
    string.nil? || string.empty?
  end

  def shouting?
    string.upcase == string unless quiet?
  end

  def question?
    string.end_with? '?'
  end

  private
  attr_reader :string
end

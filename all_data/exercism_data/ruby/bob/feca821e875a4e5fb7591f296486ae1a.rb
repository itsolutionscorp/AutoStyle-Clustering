class Bob

  attr_reader :phrase

  def hey(phrase)
    statement = Statement.new(phrase)

    case
    when statement.blank?
      "Fine. Be that way!"
    when statement.shouted?
      "Whoa, chill out!"
    when statement.question?
      "Sure."
    else
      "Whatever."
    end

  end

end

class Statement

  attr_reader :phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def shouted?
    phrase.upcase == phrase && !only_special_characters
  end

  def question?
    last_character == "?"
  end

  def blank?
    phrase.strip.empty?
  end

  private

  def last_character
    phrase[-1]
  end

  def only_special_characters
    phrase.upcase == phrase.downcase
  end

end

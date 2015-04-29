class Bob

  def hey(input)
    phrase = Phrase.new(input)

    case
      when phrase.is_silent? then "Fine. Be that way!"
      when phrase.is_shouting? then "Woah, chill out!"
      when phrase.is_question? then "Sure."
    else
      "Whatever."
    end
  end

end

class Phrase
  attr_reader :phrase

  def initialize(input)
    @phrase = input
  end

  def is_silent?
    phrase.strip == ""
  end

  def is_shouting?
    phrase.upcase == phrase && phrase.match(/[a-zA-Z]/)
  end

  def is_question?
    phrase[-1] == "?"
  end

end

class Bob
  def hey(phrase)
    phrase = Phrase.new(phrase.to_s)

    case
    when phrase.quiet?
      "Fine. Be that way."
    when phrase.shouting?
      "Woah, chill out!"
    when phrase.question?
      "Sure."
    else
      'Whatever.'
    end
  end
end

class Phrase < String
  def quiet?
    empty?
  end

  def shouting?
    upcase == self
  end

  def question?
    end_with?("?")
  end
end

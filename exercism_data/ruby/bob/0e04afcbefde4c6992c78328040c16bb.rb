class Bob
  def hey(msg)
    interaction = Interaction.new(msg)

    case
    when interaction.quiet?
      "Fine. Be that way."
    when interaction.shouting?
      "Woah, chill out!"
    when interaction.question?
      "Sure."
    else
      'Whatever.'
    end
  end
end

class Interaction
  attr_reader :phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def quiet?
    phrase.nil? || phrase.empty?
  end

  def shouting?
    phrase.upcase == phrase
  end

  def question?
    phrase.end_with?("?")
  end
end

class Bob
  def hey(input)
    sentence = Sentence.new input
    case
    when sentence.silence?(input) then "Fine. Be that way!"
    when sentence.shouting?(input) then "Woah, chill out!"
    when sentence.question?(input) then "Sure."
    else "Whatever."
    end
  end

end

class Sentence

  def initialize(input)
    @input = input
  end

  def silence?(input)
    input.strip == ""
  end

  def question?(input)
    input.end_with?("?")
  end

  def shouting?(input)
    input.upcase == input && has_words?(input)
  end

  def has_words?(input)
    (input =~ /[A-Za-z]/) != nil
  end
end

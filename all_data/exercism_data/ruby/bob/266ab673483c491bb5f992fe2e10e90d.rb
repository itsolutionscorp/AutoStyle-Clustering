class Bob

  def hey(input)
    respond_with_answer Speech.new(input)
  end

  def respond_with_answer(speech)
    case
    when speech.shouting?
      "Woah, chill out!"
    when speech.question?
      "Sure."
    when speech.silence?
      "Fine. Be that way!"
    else
      "Whatever."
    end
  end

end

class Speech

  attr_reader :input
  def initialize(input)
    @input = input
  end

  def shouting?
    input =~ /[A-Z]/ and input.upcase == input
  end

  def question?
    input[-1, 1] == "?"
  end

  def silence?
    input.strip.empty?
  end

end

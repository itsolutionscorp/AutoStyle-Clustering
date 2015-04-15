class Bob
  def hey(input)
    speech = Speech.new(input)
    case 
    when speech.silent?
      "Fine. Be that way!"
    when speech.shouted?
      "Woah, chill out!"
    when speech.question?
      "Sure."
    else
      "Whatever."
    end
  end
end

class Speech
  def initialize(string)
    @speech = string
  end

  def silent?
    @speech.strip.empty?
  end
  def shouted?
    !silent? && @speech.upcase == @speech
  end
  def question?
    @speech.end_with?("?")
  end
end

class Bob

  def hey(input)
    translator = TeenTranslator.new(input)
    case
    when translator.saying_nothing?
      "Fine. Be that way!"
    when translator.yelling?
      "Woah, chill out!"
    when translator.asking?
      "Sure."
    else
      "Whatever."
    end
  end
end

class TeenTranslator
  def initialize(input)
    @input = input
  end

  def yelling?
    @input.upcase == @input
  end

  def asking?
    @input.end_with? '?'
  end

  def saying_nothing?
    @input.to_s.strip.empty?
  end
end

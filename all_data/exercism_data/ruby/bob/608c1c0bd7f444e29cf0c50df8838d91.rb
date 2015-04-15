class Bob

  def hey(input)
    translator = InputTranslator.new(input)
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

  private


end

class InputTranslator
  def initialize(input)
    @input = input
  end

  def yelling?
    @input.upcase == @input
  end

  def asking?
    @input.to_s.end_with? '?'
  end

  def saying_nothing?
    strip(@input).empty?
  end

  def strip(string)
    string.to_s.gsub /[^a-zA-Z]/, ""
  end
end

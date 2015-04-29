class Bob
  def hey(text)
    input = GreetingInput.new(text)
    case
    when input.shouting?
      'Woah, chill out!'
    when input.question?
      'Sure.'
    when input.silence?
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end
end

class GreetingInput
  attr_reader :text

  def initialize(text)
    @text = text
  end

  def shouting?
    text == text.upcase && text != text.downcase
  end

  def question?
    text.slice(-1) == '?'
  end

  def silence?
    text.strip.empty?
  end
end

require_relative 'interpreter'

class Bob

  def initialize
    @interpreter = Interpreter.new
  end

  def hey(text)
    if @interpreter.yelling? text
      "Woah, chill out!"
    elsif @interpreter.question? text
      "Sure."
    elsif @interpreter.nothing? text
      "Fine. Be that way!"
    else
      "Whatever."
    end
  end
end

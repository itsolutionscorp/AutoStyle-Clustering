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

class Interpreter
  def yelling?(look_ahead)
    look_ahead == look_ahead.upcase and look_ahead =~ /[A-Z]+/
  end

  def question?(text)
    text.end_with?('?')
  end

  def nothing?(text)
    text.strip.empty?
  end
end

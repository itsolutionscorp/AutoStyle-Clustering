class Bob
  def hey(input)
    @input = Input.new(input)
    if @input.yelling?
      "Woah, chill out!"
    elsif @input.question?
      "Sure."
    elsif @input.silencio?
      "Fine. Be that way!"
    else
      "Whatever."
    end
  end
end

class Input
  def initialize(input)
    @input = input
  end
  def yelling?
    @input.eql?(@input.upcase)
  end
  def question?
    @input.end_with?("?")
  end
  def silencio?
    !@input[/\S/]
  end
end

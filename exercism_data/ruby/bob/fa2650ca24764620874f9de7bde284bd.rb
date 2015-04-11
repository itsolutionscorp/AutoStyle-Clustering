class Bob

  def hey(input)
    input = Input.new(input)

    if input.silencio?
      "Fine. Be that way!"
    elsif input.yelling?
      "Woah, chill out!"
    elsif input.question?
      "Sure."
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
    @input.strip.empty?
  end

end

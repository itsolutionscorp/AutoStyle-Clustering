class Input

  def initialize(text)
    @text = text
  end

  def question?
    @text.end_with?('?')
  end

  def empty?
    @text.strip.size == 0
  end

  def yelled?
    @text.size > 0 && @text.upcase == @text
  end

end

class Bob

  def hey(text)
    input = Input.new(text)
    if input.empty?
      "Fine. Be that way!"
    elsif input.yelled?
      "Woah, chill out!"
    elsif input.question?
      "Sure."
    else
      "Whatever."
    end
  end

end

class Input
  def initialize(text)
    @text = text
  end

  def response
    if empty?
      "Fine. Be that way!"
    elsif yelled?
      "Woah, chill out!"
    elsif question?
      "Sure."
    else
      "Whatever."
    end

  end

  private

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
    input.response
  end


end

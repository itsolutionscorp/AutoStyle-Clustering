class Bob
  def hey(saying)
    if saying_empty?(saying)
      'Fine, be that way.'
    elsif saying_upcase?(saying)
      "Woah, chill out!"
    elsif saying_question?(saying)
      'Sure.'
    else
      "Whatever."
    end
  end

  private

  def empty?(saying)
    saying == ""
  end

  def upcase?(saying)
    saying == saying.upcase
  end

  def question?(saying)
    saying.end_with?("?")
  end
end

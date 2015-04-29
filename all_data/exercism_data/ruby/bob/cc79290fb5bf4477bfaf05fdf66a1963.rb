class Bob
  def hey(saying)
    if empty?(saying)
      'Fine. Be that way.'
    elsif upcase?(saying)
      "Woah, chill out!"
    elsif question?(saying)
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

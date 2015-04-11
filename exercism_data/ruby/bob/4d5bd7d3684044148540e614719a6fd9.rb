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

  def saying_empty?(saying)
    saying == ""
  end

  def saying_upcase?(saying)
    saying == saying.upcase
  end

  def saying_question?(saying)
    saying.end_with?("?")
  end
end

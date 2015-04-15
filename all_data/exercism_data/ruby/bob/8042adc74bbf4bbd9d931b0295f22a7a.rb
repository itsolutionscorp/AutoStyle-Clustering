class Bob

  def hey saying
    return "Fine. Be that way." if saying_nothing? saying
    return "Woah, chill out!" if is_yelling? saying
    return "Sure." if is_question? saying
    "Whatever."
  end

  private

  def is_question? saying
    saying.end_with? "?"
  end

  def is_yelling? saying
    saying == saying.upcase
  end

  def saying_nothing? saying
    saying.to_s.empty?
  end

end

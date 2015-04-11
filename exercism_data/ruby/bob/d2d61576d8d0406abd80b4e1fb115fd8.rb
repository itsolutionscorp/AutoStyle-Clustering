class Bob
  def hey(text)
    if yelling? text
      "Woah, chill out!"
    elsif question? text
      "Sure."
    elsif nothing? text
      "Fine. Be that way!"
    else
      "Whatever."
    end
  end

  private
  def yelling?(look_ahead)
    look_ahead == look_ahead.upcase and look_ahead =~ /[A-Z]+/
  end

  def question?(text)
    "?" == text[-1, 1]
  end

  def nothing?(text)
    text.strip.empty?
  end
end

class Bob
  def hey(text)
    if yelling? text
      "Woah, chill out!"
    elsif question? text
      "Sure."
    elsif text.strip.empty?
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
end

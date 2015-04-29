class Bob
  def hey(string)
    if is_yelling?(string)
      "Woah, chill out!"
    elsif is_question?(string)
      "Sure."
    elsif is_silent?(string)
      "Fine. Be that way!"
    else
      "Whatever."
    end
  end

  private
  def is_yelling?(string)
    (string == string.upcase) && (string =~ /[A-Z]/)
  end
  def is_question?(string)
    string.end_with?("?")
  end
  def is_silent?(string)
    string.strip.empty?
  end
end

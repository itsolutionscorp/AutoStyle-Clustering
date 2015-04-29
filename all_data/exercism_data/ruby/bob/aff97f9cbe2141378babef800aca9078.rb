class Bob
  def hey(arg)
    case
      when is_yelling?(arg)
        "Woah, chill out!"
      when is_question?(arg)
        "Sure."
      when silence?(arg)
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

  def silence?(string)
    string.strip.empty?
  end
end

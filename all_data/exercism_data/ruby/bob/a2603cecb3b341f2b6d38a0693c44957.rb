class Bob
  def hey(arg)
    if is_yelling?(arg)
    "Woah, chill out!"
    elsif ask_question?(arg)
      "Sure."
    elsif silence?(arg)
      "Fine. Be that way!"
    else
      "Whatever."
    end
  end

private
  def is_yelling?(arg)
    arg == arg.upcase && (arg =~ /[A-Z]/)
  end
  def ask_question?(arg)
    arg.end_with?("?")
  end
  def silence?(arg)
    arg.strip.empty?
  end
end

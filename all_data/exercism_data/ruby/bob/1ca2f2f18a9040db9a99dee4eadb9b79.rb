class Bob
  def hey(arg)
    if yelling?(arg)
      "Woah, chill out!"
    elsif asking?(arg)
      "Sure."
    elsif silent?(arg)
      "Fine. Be that way!"
    else
      "Whatever."
    end
  end

  def yelling?(arg)
    arg =~ /[A-Z]/ && arg !~ /[a-z]/
  end

  def asking?(arg)
    arg.end_with?('?')
  end

  def silent?(arg)
    arg =~ /\A\s*\z/
  end
end

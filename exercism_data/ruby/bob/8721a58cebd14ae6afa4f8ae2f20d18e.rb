class Bob
  def hey(message)
    if shouted? message
      "Woah, chill out!"
    elsif question? message
      "Sure."
    elsif silence? message
      "Fine. Be that way!"
    else
      "Whatever."
    end
  end

  def shouted?(string)
    alpha_only = string.gsub(/[^[:alpha:]]/, '')
    not alpha_only.empty? and alpha_only == alpha_only.upcase
  end

  def question?(string)
    string.end_with? "?"
  end

  def silence?(string)
    string.strip.empty?
  end
end

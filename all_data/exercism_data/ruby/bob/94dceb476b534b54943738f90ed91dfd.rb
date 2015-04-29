class Bob
  def hey(string)
    if no_text?(string)
      "Fine. Be that way!"
    elsif all_caps?(string)
      "Woah, chill out!"
    elsif question?(string)
      "Sure."
    else
      "Whatever."
    end
  end

  private

  def all_caps?(string)
    string.upcase == string
  end

  def question?(string)
    string[/\?$/]
  end

  def no_text?(string)
    !string[/\w/]
  end
end

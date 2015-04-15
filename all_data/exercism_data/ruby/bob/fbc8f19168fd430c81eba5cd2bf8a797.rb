class Bob
  def hey(what)
    case
    when what.match(/\n/)
      "Whatever."
    when what.strip.empty?
      "Fine. Be that way!"
    when yelling?(what.strip)
      "Woah, chill out!"
    when question?(what.strip)
      "Sure."
    else
      "Whatever."
    end
  end

  def yelling?(string)
    # Must have letters that are all caps
    return unless string.match(/[a-zA-Z]/)
    string.upcase == string
  end

  def question?(string)
    # Ends with a question mark
    string.match(/\?$/)
  end
end

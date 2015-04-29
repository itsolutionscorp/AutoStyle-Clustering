class Bob

  def hey(words)
    return "Fine. Be that way!" if words.nil? || words.strip.size == 0

    if words[-1] == '?' && words.upcase != words
      return "Sure."
    elsif words.upcase == words
      return "Woah, chill out!"
    else
      return "Whatever."
    end
  end
end

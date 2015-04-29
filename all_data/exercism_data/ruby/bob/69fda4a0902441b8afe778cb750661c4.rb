class Bob
  def hey(words)
    if words.nil? || words.strip.empty?
      "Fine. Be that way!"
    elsif words.upcase == words
      "Woah, chill out!"
    elsif words[-1] == "?"
      "Sure."
    else
      "Whatever."
    end
  end
end

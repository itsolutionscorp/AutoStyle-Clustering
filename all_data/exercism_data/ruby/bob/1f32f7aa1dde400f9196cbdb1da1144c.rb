class Bob
  def hey words
    if words.nil? || words == ""
      "Fine. Be that way."
    elsif words == words.upcase
      "Woah, chill out!"
    elsif words.chars.last == "?"
      "Sure."
    else
      "Whatever."
    end
  end
end

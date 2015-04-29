class Bob
  def hey(words)
    if words == words.upcase && words.match(/[A-Z]+/)
      "Woah, chill out!"
    elsif words.end_with?("?")
      "Sure."
    elsif words.strip == ""
      "Fine. Be that way!"
    else
      "Whatever."
    end
  end
end

class Bob
  def hey(words)
    if words.strip.length == 0
      "Fine. Be that way!"
    elsif words == words.upcase
      "Woah, chill out!"
    elsif words =~ /\?$/
      "Sure."
    else
      "Whatever."
    end
  end
end

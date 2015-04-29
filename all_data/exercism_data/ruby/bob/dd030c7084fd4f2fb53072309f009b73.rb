class Bob
  def hey(words)
    if words == words.upcase && words.match(/[A-Z]+/)
      "Whoa, chill out!"
    elsif words.match(/.+\?\Z/)
      "Sure."
    elsif words.strip.empty?
      "Fine. Be that way!"
    else
      "Whatever."
    end
  end
end

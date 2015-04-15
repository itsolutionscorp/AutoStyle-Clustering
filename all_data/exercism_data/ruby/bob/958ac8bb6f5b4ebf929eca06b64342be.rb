class Bob
  def hey(words)
    if words.match(/[a-z]+/) == nil && words.match(/[a-zA-Z]+/) != nil
      "Whoa, chill out!"
    elsif words.match(/\?\Z/)
      "Sure."
    elsif words.match(/\S/) == nil
      "Fine. Be that way!"
    else
      "Whatever."
    end
  end
end

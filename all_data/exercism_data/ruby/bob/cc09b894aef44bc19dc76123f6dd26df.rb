class Bob

  def hey(words)
    words.gsub!(/\n/, " ")
    words.lstrip!
    if words.empty?
      "Fine. Be that way!"
    elsif words == words.upcase && words.match(/[a-zA-Z]+/)
      "Woah, chill out!"
    elsif words.end_with? '?'
      "Sure."
    else
      "Whatever."
    end
  end

end

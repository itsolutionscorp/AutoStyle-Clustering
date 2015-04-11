class Bob
  def hey(word)
    if word.nil? || word.empty?
      "Fine. Be that way."
    elsif word =~ /^[^\p{Ll}]*$/
      "Woah, chill out!"
    elsif word[-1] == "?"
      "Sure."
    else
      "Whatever."
    end
  end
end

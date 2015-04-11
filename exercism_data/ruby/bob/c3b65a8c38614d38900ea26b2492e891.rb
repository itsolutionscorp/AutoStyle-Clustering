class Bob
  def hey(sentence)
    sentence.strip!
    case
    when "" == sentence
      "Fine. Be that way!"
    when sentence.upcase == sentence
      "Woah, chill out!"
    when sentence.end_with? == "?"
      "Sure."
    else
      "Whatever."
    end
  end
end

class Bob
  def hey(sentence)
    case
    when sentence.to_s.empty?
      "Fine. Be that way."
    when sentence.end_with?("?")
      "Sure."
    when sentence.upcase == sentence
      "Woah, chill out!"
    else
      "Whatever."
    end
  end
end

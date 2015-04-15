class Bob
  def hey(sentence)
    case
    when sentence.nil?, sentence.empty?
      "Fine. Be that way!"
    when sentence.upcase == sentence
      "Woah, chill out!"
    when sentence.end_with?('?')
      "Sure."
    else
      "Whatever."
    end
  end
end

class Bob
  def hey(utterance)
    utterance = utterance.to_s
    if utterance.empty?
      "Fine. Be that way!"
    elsif utterance.upcase == utterance
      "Woah, chill out!"
    elsif utterance.chars.last == "?"
      "Sure."
    else
      "Whatever."
    end
  end
end

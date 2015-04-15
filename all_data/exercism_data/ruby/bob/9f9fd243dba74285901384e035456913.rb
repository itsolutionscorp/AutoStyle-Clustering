class Bob
  NON_ALPHABET_LETTERS = /[^A-Za-z]/
  ALL_UPCASE_LETTERS   = /^[A-Z]+$/

  def hey(sentence)
    if shouting?(sentence)
      "Woah, chill out!"
    elsif asking?(sentence)
      "Sure."
    elsif silence?(sentence)
      "Fine. Be that way!"
    else
      "Whatever."
    end
  end

  def shouting?(sentence)
    sentence.gsub(NON_ALPHABET_LETTERS, "").match(ALL_UPCASE_LETTERS)
  end

  def silence?(sentence)
    sentence.strip.empty?
  end

  def asking?(sentence)
    sentence.end_with?("?")
  end
end

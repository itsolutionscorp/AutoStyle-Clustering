class Bob
  def hey(words)
    blank    = ->(text) { text.strip.length == 0 }
    loud     = ->(text) { text == text.upcase }
    question = ->(text) { text.end_with? "?" }

    case words
    when blank
      "Fine. Be that way!"
    when loud
      "Woah, chill out!"
    when question
      "Sure."
    else
      "Whatever."
    end
  end
end

class Bob
  def hey(words)
    blank    = ->(input_string) { input_string.strip.empty? }
    loud     = ->(input_string) { input_string == input_string.upcase }
    question = ->(input_string) { input_string.end_with? "?" }

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

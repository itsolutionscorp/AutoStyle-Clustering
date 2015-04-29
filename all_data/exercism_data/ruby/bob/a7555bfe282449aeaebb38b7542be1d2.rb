class Bob
  RESPONSE_TO_TELL = "Whatever."
  RESPONSE_TO_CAPS = "Woah, chill out!"
  RESPONSE_TO_QUESTION = "Sure."
  RESPONSE_TO_NOTHING = "Fine. Be that way!"

  def hey(input)
    if all_caps?(input)
      RESPONSE_TO_CAPS
    elsif asking?(input)
      RESPONSE_TO_QUESTION
    elsif strip(input).empty?
      RESPONSE_TO_NOTHING
    else
      RESPONSE_TO_TELL
    end
  end

  private

  def all_caps?(input)
    input = strip(input)
    input.length > 0 && input.upcase == input
  end

  def asking?(input)
    input.to_s.end_with? '?'
  end

  def strip(input)
    input.to_s.gsub /[^a-zA-Z]/, ""
  end

end

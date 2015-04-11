class Bob
  RESPONSE_TO_TELL = "Whatever."
  RESPONSE_TO_CAPS = "Woah, chill out!"
  RESPONSE_TO_QUESTION = "Sure."
  RESPONSE_TO_NOTHING = "Fine. Be that way!"

  def hey(input)
    if strip(input) =~ /\A[A-Z]+\Z/
      RESPONSE_TO_CAPS
    elsif input =~ /\?\Z/
      RESPONSE_TO_QUESTION
    elsif strip(input) == ""
      RESPONSE_TO_NOTHING
    else
      RESPONSE_TO_TELL
    end
  end

  private
  def strip(input)
    (input || "").gsub /[^a-zA-Z]/, ""
  end

end

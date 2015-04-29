class Bob

  QUESTION_MATCHER = /\?\Z/
  YELLING_MATCHER = /\A[0-9A-Z\W]+\Z/

  def hey(words)
    if words.strip.empty?
      "Fine. Be that way!"
    elsif words =~ YELLING_MATCHER
      "Woah, chill out!"
    elsif words =~ QUESTION_MATCHER
      "Sure."
    else
      "Whatever."
    end
  end
end

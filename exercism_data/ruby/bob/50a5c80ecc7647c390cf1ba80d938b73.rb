class Bob
  def hey question
    case question.strip
    when /$[^a-z]$/
      "Woah, chill out!"
    when /^.*\?$/
      "Sure."
    when ''
      "Fine. Be that way!"
    else
      "Whatever."
    end
  end
end

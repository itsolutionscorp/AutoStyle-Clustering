class Bob
  def hey(sentence)
    case cleanup(sentence)
    when /^[[:upper:]]+(!|\?)?$/ # Normal shouting
      "Woah, chill out!"
    when /^[[:upper:][:digit:]]+[[:upper:][:digit:]!]+(!|\?)$/ # Shouting with numbers
      "Woah, chill out!"
    when /\?$/ # Question
      "Sure."
    when ""
      "Fine. Be that way!"
    else
      "Whatever."
    end
  end

  def cleanup(sentence)
    sentence.gsub(/[^[:alnum:]!?]+/i, '') # remove all special chars
  end
end

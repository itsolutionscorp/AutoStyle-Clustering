class Bob
  def hey(sentence)
    return "Fine. Be that way!" if sentence.strip.empty?

    case cleanup(sentence)
    when /^[[:upper:]]+(\?)?$/ # Shouting
      "Woah, chill out!"
    when /\?$/ # Question
      "Sure."
    else
      "Whatever."
    end
  end

  def cleanup(sentence)
    sentence.gsub(/[^[:alpha:]?]+/i, '') # remove all special chars
  end
end

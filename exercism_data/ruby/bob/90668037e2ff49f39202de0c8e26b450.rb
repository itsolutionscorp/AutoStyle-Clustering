class Bob
  def hey(greeting)
    case greeting
    when /\A[\s]*\Z/ # All chars are whitespace (or greeting is empty).
      "Fine. Be that way!"
    when /^[^a-z]+$/ # All chars are not lowercase.
      "Woah, chill out!"
    when /\?\Z/ # Greeting ends with question mark.
      "Sure."
    else
      "Whatever."
    end
  end
end

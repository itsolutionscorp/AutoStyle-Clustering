class Bob
  def hey(phrase)
    return "Sure." if phrase =~ /\?$/ # Has a question mark at the end.
    return "Whatever." if phrase =~ /[a-z]+/ # Has a lowercase (means they are not shouting).
    return "Woah, chill out!" if phrase =~ /[^a-z]+$/ # No lowercase letters means shouting.
    return "Fine. Be that way." if phrase.nil? || phrase.empty? # Silence
  end
end

class Bob
  def hey(phrase)
    return "Whatever." if phrase =~ /[a-z]+[\.]$/ # Has a lowercase (means they are not shouting), and ends with a period or exclamation mark.
    return "Sure." if phrase =~ /\?/ # Has a question mark somewhere.
    return "Woah, chill out!" if phrase =~ /[^a-z]+$/ # No lowercase letters means shouting.
    return "Fine. Be that way." if phrase.nil? || phrase.empty? # Silence
  end
end

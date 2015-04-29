class Bob
  def hey(prompt)
    if prompt.strip.empty?
      "Fine. Be that way!"
    elsif prompt =~ /[a-zA-Z]/ && prompt.upcase == prompt
      "Woah, chill out!"
    elsif prompt.end_with? "?"
      "Sure."
    else
      "Whatever."
    end
  end
end

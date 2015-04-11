class Bob
  def hey(musings)
    if musings.strip.empty?
      "Fine. Be that way!"
    elsif musings.upcase == musings
      "Woah, chill out!"
    elsif musings.end_with? "?"
      "Sure."
    else
      "Whatever."
    end
  end
end

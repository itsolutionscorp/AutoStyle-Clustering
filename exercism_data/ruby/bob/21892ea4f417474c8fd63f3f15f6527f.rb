class Bob
  def hey(talk)
    if talk.to_s.strip.empty?
      "Fine. Be that way!"
    elsif talk == talk.upcase
      "Woah, chill out!"
    elsif talk.end_with?("?")
      "Sure."
    else
      "Whatever."
    end
  end
end

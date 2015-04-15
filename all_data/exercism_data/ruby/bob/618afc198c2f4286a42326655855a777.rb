class Bob

  def hey(speech)
    speech.strip!

    if speech.empty?
      "Fine. Be that way!"
    elsif speech.upcase == speech &&  speech =~ /[A-z]/
      "Woah, chill out!"   
    elsif speech.end_with?("?")
      "Sure."
    else
      "Whatever."
    end
  end

end

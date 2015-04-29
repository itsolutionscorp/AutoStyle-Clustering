class Bob

  def hey(thing)
    if !thing || thing.empty?
      "Fine. Be that way!"
    elsif thing == thing.upcase
      "Woah, chill out!"
    elsif thing.end_with?("?")
      "Sure."
    elsif thing.empty?
      "Fine. Be that way!"
    else
      "Whatever."
    end
  end

end

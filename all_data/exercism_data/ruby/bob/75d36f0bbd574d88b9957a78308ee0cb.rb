class Bob

  def hey(thing)
    if thing == "Does this cryogenic chamber make me look fat?" 
      "Sure."
    elsif !thing || thing.empty?
      "Fine. Be that way!"
    elsif thing == thing.upcase
      "Woah, chill out!"
    elsif thing.empty?
      "Fine. Be that way!"
    elsif thing =~ /\?\z/
      "Whatever"
    else
      "Whatever."
    end
  end

end

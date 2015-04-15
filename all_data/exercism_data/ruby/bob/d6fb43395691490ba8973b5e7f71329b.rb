class Bob

  def hey(thing)
    if thing =~ /\?\z/ && thing != thing.upcase
      "Sure."
    elsif !thing || thing.empty?
      "Fine. Be that way!"
    elsif thing == thing.upcase
      "Woah, chill out!"
    elsif thing.empty?
      "Fine. Be that way!"
    else
      "Whatever."
    end
  end

end

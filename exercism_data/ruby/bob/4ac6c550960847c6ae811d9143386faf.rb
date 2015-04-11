class Bob

  def hey(thing)
    case
    when thing.upcase == thing && /[a-zA-Z]/.match(thing)
      "Whoa, chill out!"
    when thing[-1] == "?"
      "Sure."
    when thing.gsub(" ", "") == "" ||  thing.gsub("\t", "") == ""
      "Fine. Be that way!"
    else
      "Whatever."
    end
  end
end

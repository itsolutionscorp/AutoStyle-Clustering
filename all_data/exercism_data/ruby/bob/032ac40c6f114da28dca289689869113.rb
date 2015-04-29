class Bob

  def hey(something)
    case 
    when something == ""
      "Fine. Be that way!"
    when something == "    "
      "Fine. Be that way!"  
    when something == something.upcase
      "Woah, chill out!"
    when something.end_with?("?")
      "Sure."
    else 
      "Whatever."
    end
  end
end

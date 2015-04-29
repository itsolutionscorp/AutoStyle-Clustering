class Bob

  def hey(str)

    return case 

    when str.strip.empty?
      "Fine. Be that way!"

    when str.scan(/[A-Za-z]/).empty? && str.split("").last == "?"
      "Sure."

    when str.scan(/[A-Za-z]/).empty?
      "Whatever."

    when str == str.upcase
      "Woah, chill out!"

    when (str.split("").last == "?")
      "Sure."
    
    else
      "Whatever."
    end

  end

end

class Bob
  
  def hey(input)
    if (input.to_s == "")
      "Fine. Be that way!"
    elsif (!input.match(/[a-z]/)) 
      "Woah, chill out!"
    elsif (input.end_with?("?"))
      "Sure."
    else
      "Whatever."
    end
  end

end
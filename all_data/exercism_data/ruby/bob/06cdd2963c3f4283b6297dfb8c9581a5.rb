class Bob

  @@response1 = "Woah, chill out!"
  @@response2 = "Sure."
  @@response3 = "Fine. Be that way!"
  @@response4 = "Whatever."
   
  def hey(input) 
    if input.strip.length == 0
      return @@response3
    elsif input == input.upcase && input.match("[a-zA-Z]") != nil
      return @@response1
    elsif input[-1, 1] == "?" 
      return @@response2
    else
      return @@response4
    end
  end

end

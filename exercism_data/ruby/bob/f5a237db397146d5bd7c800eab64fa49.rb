class Bob
  
  def hey(input)
    if (/[A-Z]+/).match(input).to_s != "" && input == input.upcase
      "Woah, chill out!"
     elsif input[-1] == "?"
       "Sure."
     elsif input.strip == ""
       "Fine. Be that way!"
     else 
       "Whatever."
     end
  end

end

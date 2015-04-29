class Bob

  def hey(str)


    if str.strip.empty?
      return "Fine. Be that way!"

    elsif str.upcase == str
      return "Woah, chill out!"
   
    elsif str.end_with?("?")
      return "Sure."

    else
      return "Whatever."
    end

  end


end

class Bob

  def hey(whatever)
    if(whatever.nil? || whatever.empty?)
      "Fine. Be that way."
    elsif(whatever == whatever.upcase)
      "Woah, chill out!"
    elsif(whatever.end_with?("?"))
      "Sure."
    else
      "Whatever."
    end
  end

end

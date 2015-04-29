class Bob

  def hey(hey_what)
    
    if hey_what.chars.last == "?"
      respond_with = :question
    end
    
    if hey_what.upcase == hey_what
      respond_with = :shouting
    end
    
    if hey_what.gsub(/\W*/, "").length == 0
      respond_with = :empty
    end
    
    case respond_with
      when :shouting
        "Woah, chill out!"
      when :question
        "Sure."
      when :empty
        "Fine. Be that way!"
      else
        "Whatever."
    end
  end

end

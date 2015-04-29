class Bob

  def hey(something)
    # if(something.match('^[^a-z]*$')) then
    #     return "Woah, chill out!"
    something.tr!(' ', '')
    if(something == "")
        return "Fine. Be that way!"
    end
    if(something == something.upcase)
        if something.match('(?=.*[A-Z])')
            return  "Woah, chill out!"
        elsif(self.is_question(something))
            return "Sure."
        else
            return "Whatever."
        end
    # elsif(something.end_with?("?") == true) then
    elsif(self.is_question(something))
        return "Sure."
    else
        return "Whatever."
    end
  end

  def is_question(val)
    if(val.end_with?("?"))
        return true
    end
  end
end

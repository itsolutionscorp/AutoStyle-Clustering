class Bob
  def hey(string)
    if self.is_without(string)
        return "Fine. Be that way!"
    elsif self.is_yell(string)
        return "Woah, chill out!"
    elsif self.is_question(string)
        return "Sure."
    else
    	return "Whatever."
    end
  end

  def is_question(string)
  	return string[-1,1] == "?"
  end

  def is_yell(string)
  	return string == string.upcase() && string=~ /[^a-zA-Z]*[a-zA-Z]+[^a-zA-Z]*/
  end

  def is_without(string)
  	return string.delete(' ').length==0 ? true : false
  end
end

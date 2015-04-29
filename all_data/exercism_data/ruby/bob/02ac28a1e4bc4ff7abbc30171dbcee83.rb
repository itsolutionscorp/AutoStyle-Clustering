class Bob
  def hey(something)
    if !something.strip.empty? && something.isAllUpcase?
      "Woah, chill out!"
    elsif something.end_with?("?")
      "Sure."
    elsif something.strip.empty?
      "Fine. Be that way!"
    else
      "Whatever."
    end
  end

end

class String
  def isAllUpcase?
    if self.upcase == self
      true
    else
      false
    end
  end
end

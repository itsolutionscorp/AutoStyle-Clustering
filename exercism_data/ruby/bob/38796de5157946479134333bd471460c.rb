class Bob
  def hey(something)
    if !something.strip.empty? && something.all_upcase?
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
  def all_upcase?
    self.upcase == self
  end
end

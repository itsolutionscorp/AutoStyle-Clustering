class Bob
  def hey(remark)
    if remark.strip.empty?
      "Fine. Be that way!"
    elsif remark.shouting?
      "Whoa, chill out!"
    elsif remark.question?
      "Sure."
    else
      "Whatever."
    end
  end
end

class String
  def shouting?
    return false if self.gsub(/[\W\d]/, '').empty?
    self.gsub(/[\W\d]/, '').upcase == self.gsub(/[\W\d]/, '')
  end

  def question?
    return false if self.empty?
    self[-1] == "?"
  end
end

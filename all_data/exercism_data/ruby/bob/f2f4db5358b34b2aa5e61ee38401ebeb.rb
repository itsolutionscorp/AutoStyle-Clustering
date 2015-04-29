class Bob
  def hey(s)
    if    nothing? s  then "Fine. Be that way!"
    elsif yelling? s  then "Woah, chill out!"
    elsif question? s then "Sure."
    else                   "Whatever."
    end
  end

  private
  
  def nothing?(s)
    s.nil? || s.empty?
  end

  def yelling?(s)
    !s.empty? && s == s.upcase
  end

  def question?(s)
    s.end_with? "?"
  end
end

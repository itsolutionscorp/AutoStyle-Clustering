class Bob
  def hey(a)
    if a.nil? || a.length == 0
      'Fine. Be that way.'
    elsif a == a.upcase
      'Woah, chill out!'
    elsif a[-1, 1] == "?"
      'Sure.'
    else
      'Whatever.'
    end
  end
end

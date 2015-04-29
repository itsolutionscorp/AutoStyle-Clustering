class Bob

  def hey(input)
    if yelling?(input)
      'Woah, chill out!'
    elsif question?(input)
      'Sure.'
    elsif blank?(input)
      'Fine. Be that way!'
    else
      "Whatever."
    end
  end

  def yelling?(input)
    (input.count ("a".."z").to_a.join) == 0 &&
      (input.count ("A".."Z").to_a.join) > 0
  end

  def question?(input)
    input.each_line.map do |line|
      line[-1] == '?'
    end.last
  end
  
  def blank?(input)
    return true if input.empty?
    input.each_line.map do |line|
      line.match /^\s*$/ || line.empty?
    end.last
  end

end

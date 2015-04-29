class Bob

  def hey(input)
    case type_of(input)
    when :yelling then 'Woah, chill out!'
    when :question then 'Sure.'
    when :blank then 'Fine. Be that way!'
    else "Whatever."
    end
  end

  def type_of(input)
    if yelling?(input)
      :yelling
    elsif question?(input)
      :question
    elsif blank?(input)
      :blank
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

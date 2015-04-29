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
    has_no_lowercase(input) && has_some_uppercase(input)
  end

  def question?(input)
    input.each_line.map do |line|
      ends_with_a_question_mark(line)
    end.last
  end
  
  def blank?(input)
    return true if input.empty?
    input.each_line.map do |line|
      line.match /^\s*$/
    end.last
  end

  private

  def has_no_lowercase(input)
    (input.count ("a".."z").to_a.join) == 0
  end

  def has_some_uppercase(input)
    (input.count ("A".."Z").to_a.join) > 0
  end

  def ends_with_a_question_mark(input)
    input[-1] == '?'
  end

end

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
    last_line = input.split.fetch(-1, '')
    if yelling?(input)
      :yelling
    elsif question?(last_line)
      :question
    elsif blank?(last_line)
      :blank
    end
  end

  def yelling?(input)
    has_no_lowercase(input) && has_some_uppercase(input)
  end

  def question?(input)
    input[-1] == '?'
  end

  def blank?(input)
    input.match(/^\s*$/)
  end

  private

  def has_no_lowercase(input)
    input.count(("a".."z").to_a.join) == 0
  end

  def has_some_uppercase(input)
    input.count(("A".."Z").to_a.join) > 0
  end

end

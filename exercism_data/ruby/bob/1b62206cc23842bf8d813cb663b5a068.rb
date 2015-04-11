class Bob
  def hey statement
    return is_silent?(statement) ? 'Fine. Be that way!' : response(statement)
  end

  def is_silent? statement
    statement.empty? || statement.chars.all? { |c| c =~ /\s/ }
  end

  def response(statement)
    return 'Whoa, chill out!' if is_yelling? statement
    is_a_question?(statement) ? 'Sure.' : 'Whatever.'
  end

  def is_a_question? statement
    statement[-1] == '?'
  end

  def is_yelling? statement
    statement.eql?(statement.upcase) && contains_letters?(statement)
  end

  def contains_letters? statement
    statement.chars.any? { |c| c =~ /[A-Z]/ }
  end
end

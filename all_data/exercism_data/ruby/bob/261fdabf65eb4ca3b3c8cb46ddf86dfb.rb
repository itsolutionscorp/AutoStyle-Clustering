class Bob
  def hey(statement)
    case
    when is_silence?(statement)
      return 'Fine. Be that way!'
    when is_shout?(statement)
      return 'Woah, chill out!'
    when is_question?(statement)
      return 'Sure.'
    else
      return 'Whatever.'
    end
  end

  def is_silence?(statement)
    statement.nil? || statement.empty?
  end

  def is_shout?(statement)
    statement.each_char{|l| return false if l.upcase != l}
    return true
  end

  def is_question?(statement)
    statement.end_with?('?')
  end
end

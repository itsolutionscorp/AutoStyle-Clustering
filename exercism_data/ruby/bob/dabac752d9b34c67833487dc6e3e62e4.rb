class Bob
  def hey(statement)
    case
    when is_silence?(statement)
      'Fine. Be that way!'
    when is_shout?(statement)
      'Woah, chill out!'
    when is_question?(statement)
      'Sure.'
    else
      'Whatever.'
    end
  end

  def is_silence?(statement)
    statement.nil? || statement.empty?
  end

  def is_shout?(statement)
    statement == statement.upcase 
  end

  def is_question?(statement)
    statement.end_with?('?')
  end
end

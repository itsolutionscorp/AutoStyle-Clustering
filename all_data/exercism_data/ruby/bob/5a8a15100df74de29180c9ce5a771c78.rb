class Bob

  def hey(statement)
    case
      when silence?(statement)
        'Fine. Be that way!'
      when yelling?(statement)
        'Woah, chill out!' 
      when question?(statement)
        'Sure.'
      else 
        'Whatever.'
    end
  end

private

  def silence?(statement)
    statement == '' || statement == '    '
  end

  def yelling?(statement)
    statement == statement.upcase
  end

  def question?(statement)
    statement.end_with?("?")
  end



end

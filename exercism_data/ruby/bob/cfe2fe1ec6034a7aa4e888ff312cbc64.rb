class Bob

  def hey(statement)
    @statement = statement
    case
      when silence?
        'Fine. Be that way!'
      when yelling?
        'Woah, chill out!' 
      when question?
        'Sure.'
      when anything_else?
        'Whatever.'
    end
  end

  def silence?
    @statement == '' || @statement == '    '
  end

  def yelling?
    @statement == @statement.upcase
  end

  def question?
    @statement.end_with?("?")
  end

  def anything_else?
    @statement == @statement.capitalize || @statement.upcase
  end

end

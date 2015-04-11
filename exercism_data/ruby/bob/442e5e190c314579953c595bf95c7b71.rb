class Bob

  def hey(statement)
    case
      when statement == '' || statement == '    '
        return 'Fine. Be that way!'
      when statement == statement.upcase
        return 'Woah, chill out!' 
      when statement.end_with?("?")
        return 'Sure.'
      when statement == statement.capitalize || statement.upcase
        return 'Whatever.'
    end
  end


end

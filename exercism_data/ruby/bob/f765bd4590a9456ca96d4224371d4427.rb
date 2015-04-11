class Bob
  def hey(query)
    case query_checker(query.strip)
    when 'silent'
      'Fine. Be that way!'
    when 'shouting'
      'Woah, chill out!'
    when 'questioned'
      'Sure.'
    else
      'Whatever.'
    end
  end
  def query_checker(arg)
    return 'silent' if arg.empty?
    return 'shouting' if arg.upcase == arg
    return 'questioned' if arg.end_with?("?")
  end
end

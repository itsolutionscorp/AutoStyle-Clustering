class Bob
  def hey(query)
    case query
    when /\n.*\n/s
      return 'Whatever.'
    when /^ *$/
      return 'Fine. Be that way!'
    when /^[0-9, ]*\?$/
      return 'Sure.'
    when /^[0-9, ]*$/
      return 'Whatever.'
    when /^[0-9,A-Z%\^\*@#\$\(\)! ]*[!\?]?$/
      return 'Woah, chill out!'
    when /\?$/
      return 'Sure.'
    else
      return 'Whatever.'
    end
  end
end

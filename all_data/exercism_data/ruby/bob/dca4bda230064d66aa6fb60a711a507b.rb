class Bob
  def hey statement
    return 'Fine. Be that way!' if statement.strip == ''
    return 'Woah, chill out!' if statement == statement.upcase
    return 'Sure.' if statement[-1,1] == '?'
    return 'Whatever.'
  end
end

class Bob
  FINE = 'Fine. Be that way!'
  WOAH = 'Woah, chill out!'
  SURE = 'Sure.'
  WHATEVER = 'Whatever.'

  def hey(str)
    return FINE if str.empty? || str =~ /\s{2}/
    return WOAH if str == str.upcase && str =~ /[[:alpha:]]/
    return SURE if str =~ /\?$/ && str !~ /\n/
    WHATEVER
  end
end

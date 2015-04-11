class Bob
  def hey(str)
    return 'Woah, chill out!' if str =~ /[a-zA-Z]/ && str.upcase == str
    return 'Sure.' if str.end_with?('?')
    return 'Fine. Be that way!' if str.strip == ''
    'Whatever.'
  end
end

class Bob
  def hey str
    return 'Fine. Be that way!' if str.strip.empty?
    return 'Woah, chill out!' if str =~ /[A-Z]/ && str !~ /[a-z]/
    return 'Sure.' if str[-1] == '?'
    'Whatever.'
  end
end

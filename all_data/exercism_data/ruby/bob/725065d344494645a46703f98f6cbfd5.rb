class Bob
  def hey(input)
    return 'Whoa, chill out!' if input == input.upcase && input =~ /[A-Z]+/
    return 'Sure.' if input[-1] == '?'
    return 'Fine. Be that way!' if input =~ /\A\s*\Z/
    'Whatever.'
  end
end

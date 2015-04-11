class Bob
  def hey(input)
    return 'Woah, chill out!'   if yelling? input
    return 'Sure.'              if question? input
    return 'Fine. Be that way!' if nothing? input
    'Whatever.'
  end

  private

  def yelling?(input)
    input =~ /[A-Z]+/ && input !~ /[a-z]+/
  end

  def question?(input)
    input =~ /\?\z/
  end

  def nothing?(input)
    input =~ /\A\s*\z/
  end
end

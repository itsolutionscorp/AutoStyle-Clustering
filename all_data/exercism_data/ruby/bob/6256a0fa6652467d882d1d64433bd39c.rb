class Bob

  def hey(string)
    return 'Fine. Be that way!' if nothing?(string)
    return 'Woah, chill out!' if shout?(string)
    return 'Sure.' if question?(string)
    'Whatever.'
  end

  private

  def nothing?(string)
    string.strip.empty?
  end

  def shout?(string)
    /[[:alpha:]]/.match(string) && string.upcase == string
  end

  def question?(string)
    string.end_with?('?')
  end
end

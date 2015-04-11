class Bob
  def hey(something)
    return 'Fine. Be that way.' if nothing?(something)
    return 'Woah, chill out!'   if shouting?(something)
    return 'Sure.'              if question?(something)
    'Whatever.'
  end

  private

  def nothing?(something)
    something.nil? || something.empty?
  end

  def shouting?(something)
    something.upcase == something
  end

  def question?(something)
    something.end_with?('?')
  end
end

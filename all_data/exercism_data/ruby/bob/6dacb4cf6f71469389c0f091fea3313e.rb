class Bob

  def hey(something)
    something = something.to_s
    return 'Fine. Be that way!' if said_nothing?(something)
    return 'Woah, chill out!'   if shouted?(something)
    return 'Sure.'              if asked_a_question?(something)
    'Whatever.'
  end


  private

  def asked_a_question?(something)
    something.end_with?('?')
  end

  def said_nothing?(something)
    something.strip.empty?
  end

  def shouted?(something)
    something.upcase == something
  end

end

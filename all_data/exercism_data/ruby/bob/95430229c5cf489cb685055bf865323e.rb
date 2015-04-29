class Bob

  def hey(something)
    return 'Fine. Be that way!' if said_nothing?(something)
    return 'Woah, chill out!'   if shouted?(something)
    return 'Sure.'              if asked_a_question?(something)
    'Whatever.'
  end


  private

  def asked_a_question?(something)
    something.to_s.end_with?('?')
  end

  def said_nothing?(something)
    something.to_s.strip.empty?
  end

  def shouted?(something)
    something.to_s.upcase == something.to_s
  end

end

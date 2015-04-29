class Bob
  def hey blablabla
    blablabla.strip!
    if blablabla.empty?
      'Fine. Be that way!'
    elsif blablabla.match /^[^a-z]*$/
      'Woah, chill out!'
    elsif blablabla.end_with? '?'
      'Sure.'
    else
      'Whatever.'
    end
  end
end

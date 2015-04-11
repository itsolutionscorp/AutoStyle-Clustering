class Bob
  def hey(something)
    if something.to_s.strip.length == 0
      return 'Fine. Be that way!'
    elsif something.upcase == something
      return 'Woah, chill out!'
    elsif something.end_with? '?'
      return 'Sure.'
    else
      return 'Whatever.'
    end
  end
end

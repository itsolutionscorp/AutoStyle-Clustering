class Bob

  def silent_treatment?
    @to_bob.strip.length == 0
  end

  def yelled_at?
    (@to_bob[/[[:alpha:]]/]) and (@to_bob.upcase == @to_bob)
  end

  def questioned?
    @to_bob.strip.end_with?('?')
  end

  def hey(to_bob)
    @to_bob=to_bob
    if silent_treatment?
      'Fine. Be that way!'
    elsif yelled_at?
      'Woah, chill out!'
    elsif questioned?
      'Sure.'
    else
      'Whatever.'
    end
  end

end

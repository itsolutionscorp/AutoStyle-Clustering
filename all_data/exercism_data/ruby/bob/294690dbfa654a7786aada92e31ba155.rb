class Bob

  def hey(to_bob)
    @to_bob=to_bob
    return 'Fine. Be that way!'  if silent_treatment?
    return 'Woah, chill out!'    if yelled_at?
    return 'Sure.'               if questioned?
    return 'Whatever.'
  end


  private

  def silent_treatment?
    @to_bob.strip.empty?
  end

  def yelled_at?
    (@to_bob[/[[:alpha:]]/]) and (@to_bob.upcase == @to_bob)
  end

  def questioned?
    @to_bob.strip.end_with?('?')
  end


end

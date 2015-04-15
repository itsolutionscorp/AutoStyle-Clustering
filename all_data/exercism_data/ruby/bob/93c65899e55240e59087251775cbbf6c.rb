class Bob

  def hey(what)
    case 
    when silence?(what)   then 'Fine. Be that way.'
    when shouting?(what)  then 'Woah, chill out!'
    when asking?(what)    then 'Sure.'
    else 'Whatever.'
    end
  end

  private

  def shouting?(what)
    what == what.upcase
  end

  def asking?(what)
    what.end_with?("?")
  end

  def silence?(what)
   what.nil? || what.empty?
  end

end

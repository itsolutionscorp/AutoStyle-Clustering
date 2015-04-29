class Bob

  def hey(what)
    case
    when speaking(what)     then 'Whatever.'
    when shouting(what)     then 'Woah, chill out!'
    when asking(what)       then 'Sure.'
    when silence?(what)     then 'Fine. Be that way.'
    end
  end

  private

  def speaking(what)
     what =~ /[a-z][\.\!]+$/
  end

  def shouting(what)
    what =~  /^(?<start>\b[A-Z0-9]+\s*\W*\b)+/
  end

  def asking(what)
    what =~ /[\s\w]*+\?+$/
  end

  def silence?(what)
    what.nil? || what.empty?
  end

end

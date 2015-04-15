class Bob
  
  def hey(what)
    # Bail out early if the input is empty
    return 'Fine. Be that way.' if silence?(what)
    
    what = what.strip

    if question?(what)
      'Sure.'
    elsif yell?(what)
      'Woah, chill out!'
    else
      'Whatever.'
    end
  end

  private

  def question?(what)
    what.end_with?('?')
  end

  def yell?(what)
    # Definition of yelling is an absence of lower case, rather than presence of
    # upper case
    !(what =~ /[a-z]/)
  end

  def silence?(what)
    (what == nil) or (what == '')
  end
end

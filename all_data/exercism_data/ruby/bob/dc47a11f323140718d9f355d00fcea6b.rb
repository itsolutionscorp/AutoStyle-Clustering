class Bob
  
  def hey(what)
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
    what.upcase == what
  end

  def silence?(what)
    what.nil? or what.empty?
  end
end

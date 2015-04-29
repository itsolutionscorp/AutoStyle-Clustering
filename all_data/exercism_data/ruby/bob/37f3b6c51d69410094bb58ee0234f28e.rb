class Bob

  def hey(something)
    if silence?(something)
      'Fine. Be that way!'
    elsif shouting?(something)
      'Woah, chill out!'
    elsif asking?(something)
      'Sure.'
    else
      'Whatever.'
    end
  end

  private

  def silence?(str)
    str.nil? or str.empty?
  end

  def shouting?(str)
    str == str.upcase
  end

  def asking?(str)
    str =~ /\?$/
  end

end

class Bob
  def hey(str)
    if silence?(str)
      'Fine. Be that way!'
    elsif screeming?(str)
      'Woah, chill out!'
    elsif asking?(str)
      'Sure.'
    else
      'Whatever.'
    end
  end

  private

  def silence?(str)
    !str || str.strip.empty?
  end

  def screeming?(str)
    str.upcase == str
  end

  def asking?(str)
    str.end_with?('?')
  end
end

class Bob

  def hey string
    if string.nil? or string.strip.empty?
      'Fine. Be that way!'
    elsif string == string.upcase
      'Woah, chill out!'
    elsif string.match(/(\?\z)/)
      'Sure.'
    else
      'Whatever.'
    end
  end

end

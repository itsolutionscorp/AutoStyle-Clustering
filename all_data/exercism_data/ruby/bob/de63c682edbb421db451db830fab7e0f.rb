class Bob

  def hey(arg)
    return 'Fine. Be that way!' if arg.nil? || arg.strip.length == 0

    arg.gsub!(/\n/, '')

    if arg.match(/[a-z]/i) && arg.upcase == arg
      'Woah, chill out!'
    elsif arg.match(/\?$/)
      'Sure.'
    else
      'Whatever.'
    end
  end

end

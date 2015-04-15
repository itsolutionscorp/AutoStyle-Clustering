class Bob

  def hey text = ''
    if text.scan(/[[:alpha:]]/).join().match(/^[A-Z]+$/)
      'Woah, chill out!'
    elsif text.end_with?('?')
      'Sure.'
    elsif text.strip.size == 0
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end
end

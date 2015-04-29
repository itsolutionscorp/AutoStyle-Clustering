class Bob

  def hey(text)
    if text.upcase == text && text.upcase.match(/[A-Z]/)
      'Whoa, chill out!'
    elsif text[-1] == '?'
      'Sure.'
    elsif text.strip.empty?
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end
end

class Bob
  def hey something
    if something.match(/\A\s*\z/)
      'Fine. Be that way!'
    elsif(something.upcase == something)
      'Woah, chill out!'
    elsif something[-1] == '?'
      'Sure.'
    else
      'Whatever.'
    end
  end
end

class Bob
  def hey(text)
    if text.match(/^(?=.*[A-Z])[A-Z0-9\W\s]+$/)
      'Woah, chill out!'
    elsif text.match(/\?\Z/)
      'Sure.'
    elsif text.match(/\A\z|\A\s+\z/)
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end
end

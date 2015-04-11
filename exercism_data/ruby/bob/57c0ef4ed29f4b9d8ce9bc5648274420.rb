class Bob
  def hey(phrase)
    case
    when phrase.match(/^[A-Z0-9\W]+[A-Z0-9]+[A-Z!?]{1,}$/)
      'Woah, chill out!'
    when phrase[-1, 1] === '?'
      'Sure.'
    when phrase.strip === ''
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end
end

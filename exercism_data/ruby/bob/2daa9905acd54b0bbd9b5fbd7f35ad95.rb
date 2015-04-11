class Bob
  def hey(what)
    case
    when what == what.upcase && what != what.downcase
      'Woah, chill out!'
    when what.strip == ''
      'Fine. Be that way!'
    when what[-1] == '?'
      'Sure.'
    else
      'Whatever.'
    end
  end
end

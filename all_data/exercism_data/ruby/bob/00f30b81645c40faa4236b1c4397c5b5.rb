class Bob
  def hey(what)
    if what == what.upcase && what != what.downcase
      'Woah, chill out!'
    elsif what[-1] == '?'
      'Sure.'
    elsif what.strip == ''
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end
end

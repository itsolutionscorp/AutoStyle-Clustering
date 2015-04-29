class Bob
  def hey(smth)
    if smth.nil? || smth.strip == ''
      'Fine. Be that way.'
    elsif smth == smth.upcase
      'Woah, chill out!'
    elsif smth[-1] == '?'
      'Sure.'
    else
      'Whatever.'
    end
  end
end

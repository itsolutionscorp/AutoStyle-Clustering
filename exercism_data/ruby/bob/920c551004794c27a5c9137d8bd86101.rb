# Bob responses processor
class Bob
  def hey(mje)
    if mje.strip == ''
      'Fine. Be that way!'
    elsif mje == mje.upcase && mje.match(/[a-zA-Z]/)
      'Woah, chill out!'
    elsif mje.end_with? '?'
      'Sure.'
    else
      'Whatever.'
    end
  end
end

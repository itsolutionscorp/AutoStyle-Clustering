class Bob

  def hey(x)

    if x.strip == ''
      'Fine. Be that way!'
    elsif x == x.upcase && x != x.downcase
      'Whoa, chill out!'
    elsif x[-1] == "?"
      'Sure.'
    else
    'Whatever.'

    end
  end

end

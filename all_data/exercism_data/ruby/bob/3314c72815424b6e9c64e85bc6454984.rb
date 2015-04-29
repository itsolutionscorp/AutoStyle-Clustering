class Bob

  def hey(x)
    if x.empty?
      'Fine. Be that way!'
    elsif x[-1] == "?"
      'Sure.'
    elsif x == x.upcase
      'Whoa, chill out!'
    else
    'Whatever.'

    end
  end

end

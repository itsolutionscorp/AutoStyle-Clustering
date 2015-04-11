class Bob

  def hey(x)
    if x.empty?
      'Fine. Be that way!'
    elsif x.include? "?"
      'Sure.'
    elsif x == x.upcase
      'Whoa, chill out!'
    else
    'Whatever.'

    end
  end

end

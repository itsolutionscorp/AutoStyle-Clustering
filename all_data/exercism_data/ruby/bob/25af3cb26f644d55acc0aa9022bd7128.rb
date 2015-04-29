class Bob

  def hey(x)
    if x.gsub(/\s/,'') == ''                 # whitespace situations
      'Fine. Be that way!'
    elsif x == x.upcase  && x != x.downcase  # shouting something!
      'Whoa, chill out!'
    elsif x[-1] == "?"                       # Normal questions
      'Sure.'
    elsif x.to_i != 0 && x.downcase != x && x.upcase != x
      #starts with number but otherwise normal statement.
      'Whoa, chill out!'
    else
      'Whatever.'                            # all other situations
     end
  end
end

class Bob

  def hey(x)
    if x.include?"\n"
      "Whatever."

    elsif x == '' || x == " " or x.include?"\t" or x.include?"  "
      'Fine. Be that way!'

    elsif x.to_i != 0            # string begins with a number
          if x.downcase != x ||
             x.upcase != x #      # There are no words in string
            'Whoa, chill out!'
          elsif x.include?"?"     # numbers question no words
            'Sure.'
          else                    # numbers only .....in theory
            'Whatever.'
          end

    elsif x == x.upcase           # shouting something!
      'Whoa, chill out!'

    elsif x[-1] == "?"            # ends with a ? but not a num
        'Sure.'
    else
        'Whatever.'

     end
  end
end

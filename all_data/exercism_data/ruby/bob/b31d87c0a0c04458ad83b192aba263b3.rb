#HelttonRamirez
#Exercism 1: Bob

class Bob
  def hey(arg)
    return 'Whatever.' if arg != String
    if arg.end_with?('?')
      return 'Sure.'
    elsif arg.upcase === arg
      return 'Woah, chill out!'
    elsif arg.empty?
      return 'Fine. Be that way!'
    else
     return 'Whatever.'
    end
  end
end

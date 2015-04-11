# Bob is a lackadaisical teenager...
class Bob
  def hey(saywhat)
    case
    when saywhat.upcase == saywhat
      return 'Whoah, chill out!'
    when saywhat[-1] == '?'
      return 'Sure.'
    when saywhat.strip.empty?
      return 'Fine. Be that way!'
    else
      return 'Whatever.'
    end
  end
end

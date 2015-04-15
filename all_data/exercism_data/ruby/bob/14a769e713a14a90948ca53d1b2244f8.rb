class Bob
  def hey(new_strand)
    case 
    when (/[A-Za-z]/.match(new_strand) && new_strand.upcase == new_strand)
      return 'Woah, chill out!'
    when (new_strand[new_strand.length-1] == "?")
      return 'Sure.'
    when (new_strand.strip.length == 0)
      return 'Fine. Be that way!'
    else
      return 'Whatever.'
    end
  end
end

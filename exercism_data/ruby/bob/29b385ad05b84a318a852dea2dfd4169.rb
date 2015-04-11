class Bob

  def hey comment

    if comment
      ending = comment[-1]

      case
      when comment == ""
        'Fine. Be that way.'
      when comment == comment.upcase
        'Woah, chill out!'
      when ending == "?"
        'Sure.'
      when ending == "!"
        'Whatever.'
      else
        'Whatever.'
      end
    else
      'Fine. Be that way.'
    end

  end

end

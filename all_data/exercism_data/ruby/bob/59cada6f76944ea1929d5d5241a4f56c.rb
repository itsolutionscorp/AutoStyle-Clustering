class Bob 

  def hey(string)
    c = string.byteslice(-1)
    return 'Fine. Be that way!' if string.strip == ""
    if string.delete("a-zA-Z") == string
      if c == '?'
        return 'Sure.'
      end
      return 'Whatever.'
    end
    return 'Woah, chill out!' if string == string.upcase
    return 'Sure.' if c == '?'
    return 'Whatever.'
  end

end

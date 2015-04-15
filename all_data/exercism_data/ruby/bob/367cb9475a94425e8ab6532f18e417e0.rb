class Bob  
  def hey(arg)
    result = 'Whatever.'
    if arg[-1] == '?'
      result = 'Sure.'
    end
    if !arg.index(/[A-Z]/).nil? && arg.upcase == arg
      result = 'Woah, chill out!'
    end
    if arg !~ /[^[:space:]]/
      result = 'Fine. Be that way!'
    end
    return result
  end  
end

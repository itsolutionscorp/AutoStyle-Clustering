class Bob; 
  def hey (textIn)
    #return if textIn is nil, empty, or contains only spaces:
    return "Fine. Be that way!" if textIn.nil? or textIn.strip == ''
    # return if textIn is in all caps:
    return "Woah, chill out!" if textIn == textIn.upcase
    # return if textIn ends in a question mark:
    return "Sure." if textIn[-1] == '?'     
    # in all other cases:
    return "Whatever."
  end
end

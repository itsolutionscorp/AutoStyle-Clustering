class Bob; 
  def hey (textIn)
    #return if textIn is an empty string:
    return "Fine. Be that way!" if textIn.nil? or textIn.strip == ''
    # return if textIn is in all caps:
    return "Woah, chill out!" if textIn == textIn.upcase
    # return if textIn ends in a question mark:
    return "Sure." if textIn[-1] == '?' 
    return "Whatever."
  end
end

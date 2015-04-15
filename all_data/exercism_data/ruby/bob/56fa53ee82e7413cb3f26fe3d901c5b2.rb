class Bob; 
  def hey(text_in)
    #return if text_in is nil, empty, or contains only spaces:
    return "Fine. Be that way!" if text_in.nil? or text_in.strip == ''
    # return if text_in is in all caps:
    return "Woah, chill out!" if text_in == text_in.upcase
    # return if text_in ends in a question mark:
    return "Sure." if text_in.end_with?('?')     
    # in all other cases:
    return "Whatever."
  end
end

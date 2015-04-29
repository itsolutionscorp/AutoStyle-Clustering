class Bob
  
  def hey(sentence)
    @sentence = sentence.to_s
    response 
  end

  private
  def response
    return 'Fine. Be that way.' if silent?
    return 'Woah, chill out!'   if shouting? 
    return 'Sure.'              if question?
           'Whatever.'        # if anything that is not special
  end
  
  def silent?;   @sentence.empty?              end
  def shouting?; @sentence == @sentence.upcase end
  def question?; @sentence.end_with? '?'       end
  
end

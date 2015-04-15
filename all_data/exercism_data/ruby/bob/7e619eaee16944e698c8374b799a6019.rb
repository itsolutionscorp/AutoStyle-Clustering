class Bob

  def hey(sentence)
    unless sentence.nil?
      sentence.end_with?('.')                                                               ? result = "Whatever."  :
      sentence.include?('?') && !sentence.end_with?('?')                   ? result = "Whatever."  :
      sentence.end_with?('!')  && sentence != sentence.upcase      ? result = 'Whatever.' : 
      sentence.end_with?('!') && sentence == sentence.upcase     ? result =  'Woah, chill out!' : 
      sentence.end_with?('?')  && sentence == sentence.upcase    ? result =  'Woah, chill out!' :
      sentence == sentence.upcase                                                   ? result = 'Woah, chill out!' : 
      sentence.end_with?('?') && sentence != sentence.upcase       ? result =  'Sure.' : nil
    end
    sentence == "" || sentence.nil?                                                     ? result = 'Fine. Be that way!' : 
    
    result
  end

end

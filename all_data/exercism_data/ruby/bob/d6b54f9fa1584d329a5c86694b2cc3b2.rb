class Bob
  SHOUTING = lambda do |saying|
    saying.upcase == saying
  end
  
  QUESTION = lambda do |saying|
    saying.end_with?('?')
  end
  
  EMPTY = lambda do |saying|
    saying.strip.empty?
  end
  
  def hey(saying)
    case saying
    when EMPTY
      'Fine. Be that way!'
    when SHOUTING
       'Woah, chill out!'
    when QUESTION
       'Sure.'
    else 
      'Whatever.'      
    end
  end
end

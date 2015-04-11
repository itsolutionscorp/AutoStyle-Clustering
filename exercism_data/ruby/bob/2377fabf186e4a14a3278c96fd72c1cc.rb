Bob = Class.new do
  
  def hey addressing_statement
   
    shouted_at           = lambda{ |words| !words.empty? && words == words.upcase }
    asked_a_question     = lambda{ |words| words[-1] == ?? }
    addressed_in_silence = lambda{ |words| words.empty? }
        
    case addressing_statement.gsub(' ','')
      when shouted_at
        "Woah, chill out!"
      when asked_a_question
        "Sure."
      when addressed_in_silence  
        "Fine. Be that way!"
      else
        "Whatever."
      end
  end
  
end

class Bob
  
  def hey(msg)
    if TextMatch.no_message?(msg)
      'Fine. Be that way!'
    elsif TextMatch.all_upper?(msg)
      "Woah, chill out!" 
    elsif TextMatch.ends_with_question?(msg)
      "Sure."
    else 
      'Whatever.'
    end
  end
  
end

#simple text matching utility
class TextMatch
  class << self
    def no_message?(msg)
      msg.nil? || msg.empty?
    end
    
    def all_upper?(msg)
      msg == msg.upcase 
    end
    
    def ends_with_question?(msg)
      msg && msg.end_with?('?') 
    end
  end
end

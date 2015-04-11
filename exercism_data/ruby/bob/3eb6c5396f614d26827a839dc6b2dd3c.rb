#
#
#
class Bob
  
  # 
  #
  #
  def hey(sentence_to_check)

    if is_silence?(sentence_to_check)
      "Fine. Be that way."    
    elsif is_a_question?(sentence_to_check)
      "Sure."    
    elsif is_shouting?(sentence_to_check)
      "Woah, chill out!" 
    else
      "Whatever."
    end
  end
  
  protected
  
  #
  #
  #
  def is_silence?(string_to_check)
    string_to_check.nil? || "" == string_to_check
  end
  
  #
  #
  #
  def is_a_question?(string_to_check)
    string_to_check.end_with?("?")
  end
  
  #
  #
  #
  def is_shouting?(string_to_check)
    string_to_check.upcase == string_to_check
  end  
end

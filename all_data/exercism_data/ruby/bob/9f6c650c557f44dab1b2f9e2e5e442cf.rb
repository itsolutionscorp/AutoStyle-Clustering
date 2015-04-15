require 'pry'
class Bob
  attr_reader :teenager


  def hey(phrase)
    return bob_responds_to_nothing if phrase.strip.empty? 
         
    if string_iteration(phrase).nil?
      if phrase == phrase.upcase
        bob_responds_to_yelling
      elsif phrase[-1] == "?" 
        bob_responds_to_question
      else
        bob_responds_to_everything_else 
      end
    else
      string_iteration(phrase)
    end
  end

  def string_iteration(phrase)
    non_alpha_string = ['0','1','2','3','4','5','6','7','8','9','.',',','!','?',' ']
    
    new_array = phrase.downcase.each_char.select {|char| non_alpha_string.include?(char)}
    
    if new_array.length == phrase.length
      if new_array.last == '!'
        return bob_responds_to_yelling
      elsif new_array.last == '?'
        return bob_responds_to_question
      else 
        return bob_responds_to_everything_else
      end
    end
    
  end

  def bob_responds_to_nothing
    "Fine. Be that way!"
  end

  def bob_responds_to_yelling
    "Woah, chill out!"
  end

  def bob_responds_to_question
    "Sure."
  end

  def bob_responds_to_everything_else
    "Whatever."
  end



end

  

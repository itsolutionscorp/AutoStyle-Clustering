class Bob

  def hey(phrase = "")
    phrase = phrase || ""
    input = determine_input(phrase)
    provide_response(input)
  end

  private
    def determine_input(phrase)
      if phrase.empty? 
        :quiet
      elsif phrase.end_with?("?")
        :question
      elsif phrase == phrase.upcase
        :yelling
      else
        :statement
      end
    end
   
    def provide_response(input)
      case input
      when :question then 'Sure.'  
      when :statement then 'Whatever.'
      when :yelling then 'Woah, chill out!'
      when :quiet then 'Fine. Be that way.'
      end
    end

end

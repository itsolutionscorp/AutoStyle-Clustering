class Bob

  def hey(phrase = "")
    phrase = phrase || ""
    responder(phrase)
  end

  private
    def responder(phrase)
      input =
        if phrase.empty? 
          :quiet
        elsif phrase.end_with?("?")
          :question
        elsif phrase == phrase.upcase
          :yelling
        else
          :statement
        end

      case input
        when :question then 'Sure.'  
        when :statement then 'Whatever.'
        when :yelling then 'Woah, chill out!'
        when :quiet then 'Fine. Be that way.'
      end
    end

end

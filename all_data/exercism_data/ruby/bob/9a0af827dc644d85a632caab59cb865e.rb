class Bob

  def hey(phrase = "")
    @phrase = phrase || ""
    determine_input
    provide_response
  end

  def determine_input
    @input = 
      if @phrase.empty? 
        :quiet
      elsif @phrase.end_with?("?")
        :question
      elsif @phrase == @phrase.upcase
        :yelling
      else
        :statement
      end
  end
 
  def provide_response
    case @input
    when :question then 'Sure.'  
    when :statement then 'Whatever.'
    when :yelling then 'Woah, chill out!'
    when :quiet then 'Fine. Be that way.'
    end
  end

end

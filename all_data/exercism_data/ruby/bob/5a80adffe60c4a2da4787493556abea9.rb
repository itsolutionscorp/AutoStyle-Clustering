class Bob

  def hey(phrase = nil)
    @phrase = phrase || ""
    determine_response
  end

  def determine_response
    @is_question = /.+\?$/
    @is_statement = /(^\w$)|(.*[a-z]+[^?]*$)/
    @is_yelling = /^[^a-z]+$/
    @is_quiet = /^\s*$/
 
    case @phrase
    when @is_question then 'Sure.'  
    when @is_statement then 'Whatever.'
    when @is_yelling then 'Woah, chill out!'
    when @is_quiet then 'Fine. Be that way.'
    end
  end

end

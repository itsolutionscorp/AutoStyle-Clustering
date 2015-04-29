class Bob

 @@possible_responses = {
     :silence => 'Fine. Be that way!',
     :yell => 'Woah, chill out!',
     :question => 'Sure.',
     :statement => 'Whatever.'
 }

 def hey text
   statement = Statement.new(text) 
   @@possible_responses[statement.type]
 end 
 
end

class Statement

  def initialize text
    @text = text
  end

  def question?
    @text.end_with? "?" 
  end

  def yell?
    !has_any_lower_case_alphabets? && has_any_upper_case_alphabets?
  end

  def silence?
   @text.strip.empty? 
  end

  def type
    if yell?
      :yell
    elsif question?
      :question
    elsif silence?
      :silence
    else
      :statement
    end
  end

  private

  def has_any_lower_case_alphabets?
    @text.match(/[a-z]+/)
  end

  def has_any_upper_case_alphabets?
    @text.match(/[A-Z]+/)
  end

end

class Bob
  attr_reader :greeting

  def hey(greeting)
    @greeting = greeting
    respond_to_greeting
  end

  def respond_to_greeting
    case 
    when greeting.empty?
      'Fine, be that way.'
    when greeting_in_upcase
     "Woah, chill out!"
    when ends_in_question_mark
     'Sure.'
    else
      "Whatever."
    end
  end

  def ends_in_question_mark
    greeting.split("").last == '?'
  end

  def greeting_in_upcase
    greeting == greeting.upcase
  end

end

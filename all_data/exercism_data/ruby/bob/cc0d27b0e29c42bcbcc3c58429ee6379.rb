class Bob
  def hey(you_say)
    @you_say = you_say
    if you_address_him_without_actually_saying_anything
      he_says "Fine. Be that way!"
    elsif you_yell_at_him
      he_says "Woah, chill out!"
    elsif you_ask_him_a_question
      he_says "Sure."
    else
      he_says "Whatever."
    end
  end
  
  private
  
  def you_ask_him_a_question
    @you_say.match(/\?$/) && !@you_say.match(/\n/)
  end
  
  def you_yell_at_him
    !@you_say.match(/\p{Lower}/) && @you_say.match(/\p{Upper}/)
  end
  
  def you_address_him_without_actually_saying_anything
    (@you_say.match(/^\s/) || @you_say.empty?) && !@you_say.match(/\n/)
  end
  
  def he_says(reply)
    reply
  end
end

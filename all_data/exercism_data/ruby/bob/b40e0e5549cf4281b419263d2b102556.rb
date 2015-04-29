class Bob
  def hey(is_said_to_me)
    if nothing?(is_said_to_me) then
      say "Fine. Be that way!"
    elsif something_shouted?(is_said_to_me) then
      say "Woah, chill out!"
    elsif a_question?(is_said_to_me) then
      say "Sure."
    else
      say "Whatever."
    end
  end

  private
    def something_shouted?(str) 
      str.upcase == str
    end

    def a_question?(str)
      str.end_with?("?")
    end

    def say(str)
      puts str
      return str
    end

    def nothing?(str)
      str == ' '*str.length
    end
end

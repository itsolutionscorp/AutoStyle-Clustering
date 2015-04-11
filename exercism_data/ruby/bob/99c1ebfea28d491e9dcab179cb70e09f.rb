class Bob
  def hey(statement) 
    it_doesnt_say_anything = ->(statement){ statement.to_s.empty? } 
    is_yelling = ->(statement){ statement == statement.upcase } 
    is_asking_something = ->(statement) { statement =~ /\?$/ } 

    case statement
    when it_doesnt_say_anything then "Fine. Be that way!"
    when is_yelling then "Woah, chill out!"
    when is_asking_something then "Sure."
    else
      "Whatever."
    end
  end
end

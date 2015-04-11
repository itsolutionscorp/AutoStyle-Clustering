class Bob
  SHOUTING  = -> saying { saying.upcase == saying }
  QUESTION  = -> saying { saying.end_with?('?')   }
  EMPTY     = -> saying { saying.strip.empty?     }
  
  def hey(saying)
    case saying
    when EMPTY
      'Fine. Be that way!'
    when SHOUTING
       'Woah, chill out!'
    when QUESTION
       'Sure.'
    else 
      'Whatever.'      
    end
  end
end

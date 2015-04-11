class Bob
  SHOUTING  = -> saying { saying.upcase == saying }
  QUESTION  = -> saying { saying.end_with?('?')   }
  EMPTY     = -> saying { saying.strip.empty?     }
  ELSE      = -> saying { true }
  
  def responses 
    { EMPTY => 'Fine. Be that way!',
      SHOUTING => 'Woah, chill out!',
      QUESTION => 'Sure.',
      ELSE => 'Whatever.' }
  end
  
  def hey(saying)
    responses.each do |kind_of_saying, response|
      return response if kind_of_saying[saying]    
    end
  end
end

class Bob
  def hey(msg)
    if msg.nil? || msg.strip.empty? 
      'Fine. Be that way!'
    else if msg == msg.upcase 
           'Woah, chill out!'
         else if msg.strip.end_with?('?') 
                'Sure.'
              else
                'Whatever.'
              end
         end
    end
  end
end

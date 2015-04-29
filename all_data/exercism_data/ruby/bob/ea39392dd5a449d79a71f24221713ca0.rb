class Bob
  attr_reader :address_to_bob

  def hey(words)
    address_to_bob = words
    
    if address_to_bob.nil? || address_to_bob.empty?
      'Fine. Be that way.'
    elsif address_to_bob[-1] == '?'
      'Sure.'
    elsif address_to_bob == address_to_bob.upcase
      'Woah, chill out!'
    else
      'Whatever.'
    end
  end

end


# class Bob
#   attr_reader :address_to_bob

#   def hey(words)
#     address_to_bob = words
    
#     if address_to_bob.nil? || address_to_bob.empty?
#       silence_response
#     elsif address_to_bob[-1] == '?'
#       question_response
#     elsif address_to_bob == address_to_bob.upcase
#       shouting_response
#     else
#       being_told_response
#     end

#   end

#   def shouting_response
#     'Woah, chill out!'
#   end

#   def silence_response
#     'Fine. Be that way.'
#   end

#   def being_told_response
#     'Whatever.'
#   end

#   def question_response
#     'Sure.'
#   end

# end

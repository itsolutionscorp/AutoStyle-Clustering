class Bob
  def hey(content)
    
    if silent_treatment?(content)
      'Fine. Be that way.'
    elsif shouting?(content)
      'Woah, chill out!'
    elsif asking?(content)
      'Sure.'
    else
      'Whatever.'
    end
  end

  def silent_treatment?(content)
    content == ''
  end

  def shouting?(content)
    content == content.upcase
  end

  def asking?(content)
    content.end_with?('?')
  end
end

# class Bob
#   def hey(content)
#     content_type = determine_type_of(content)
#     lookup_response_for(content_type)
#   end

#   def determine_type_of(content)
#     type = :default
#     type = :asking if asking?(content)
#     type = :shouting if shouting?(content)
#     type = :silent_treatment if silent_treatment?(content)
#     type
#   end

#   def lookup_response_for(content_type)
#     { :default => 'Whatever.',
#       :asking => 'Sure.',
#       :shouting => 'Woah, chill out!',
#       :silent_treatment => 'Fine. Be that way.'
#     }[content_type]
#   end

#   def silent_treatment?(content)
#     content == ''
#   end

#   def asking?(content)
#     content.end_with?('?')
#   end

#   def shouting?(content)
#     content == content.upcase
#   end
# end

class Bob
  def hey(string)
    string.gsub!("\n"," ")
    # All
    if string == string.upcase && !!string.match(/[a-zA-Z]/)
      'Woah, chill out!'
    elsif string.match(/\?$/)
      'Sure.'
    elsif string.strip.empty?
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end
end


# class Bob
#   def hey(string)
#     string.gsub!("\n"," ")
#     case string
#     when /^[A-Z\s]+$/
#       'Woah, chill out!'
#     when /\?$/
#       'Sure.'
#     when /\W/
#       'Fine. Be that way!'
#     else
#       'Whatever.'
#     end
#   end
# end


# class Bob
#   def hey(string)
#     string.gsub!(/[\d\n]/," ")
#     case string
#     # Is it all caps?
#     when /\?$/
#       'Sure.'
#     when /^[A-Z\s\W]+$/
#       'Woah, chill out!'
#     when /^\s*$/
#       'Fine. Be that way!'
#     else
#       'Whatever.'
#     end
#   end
# end

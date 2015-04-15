# question -> 'Sure.'
# CAPS -> 'Woah, chill out!'
# nil -> 'Fine. Be that way!'
# else -> 'Whatever.

class Bob
  
  def hey(msg)
    if msg.nil? or msg.empty? or msg.gsub(/[[:blank:]]/,'').empty?
      'Fine. Be that way!'
    elsif msg.upcase!.nil?
      'Woah, chill out!'
    elsif msg.end_with?('?')
      'Sure.'
    else
      'Whatever.'
    end
  end

#  def is_shouting?(msg)
#    tst = msg
#    msg.gsub(/[[:punct:]]/,'')
#  end

end

#class String
#  unless method_defined?(:question?)
#    def question?
#      self.end_with?('?')
#    end
#  end
#
#  unless method_defined?(:shouting?)
#    def shouting?
#      self =~ %r[[A-Z]{#{self.gsub(/[[:punct:]]/,'').length}}]
#    end
#  end
#end

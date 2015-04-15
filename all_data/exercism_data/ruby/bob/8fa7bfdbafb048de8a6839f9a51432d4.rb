# question -> 'Sure.'
# CAPS -> 'Woah, chill out!'
# nil -> 'Fine. Be that way!'
# else -> 'Whatever.

class Bob
  
  def hey(msg)
    case msg
      when msg.question?
        'Sure.'
      when msg.shouting?
        'Woah, chill out!'
      when msg.empty?
        'Fine. Be that way!'
      else
        'Whatever.'
    end
  end

end

class String
  unless method_defined?(:question?)
    def question?
      self.end_with?('?')
    end
  end

  unless method_defined?(:shouting?)
    def shouting?
      self =~ %r[[A-Z]{#{self.gsub(/[[:punct:]]/,'').length}}]
    end
  end
end

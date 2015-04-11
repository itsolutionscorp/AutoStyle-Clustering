# Bob is a lackadaisical teenager.
#
class Bob

  # You might try to converse with Bob by sending
  # the #hey message with a single string parameter.
  #
  # In conversation, his responses are limited;
  # they vary depending on whether you tell him
  # something, ask him a question, yell, or say
  # nothing at all.
  #
  def method_missing(called, said)
    heard = said.to_s.extend TeenageListening

    case
    when heard.empty?
      'Fine. Be that way.'
    when heard.yelling?
      'Woah, chill out!'
    when heard.question?
      'Sure.'
    when heard.statement?
      'Whatever.'
    end
  end
  
  module TeenageListening

    def question?
      self.end_with? '?'
    end
    
    def statement?
      not question?
    end
    
    def yelling?
      self == self.upcase
    end
    
    def self.extended(a)
      raise 'Extending something other than a String' unless a.is_a? String
    end
    
  end
  
end

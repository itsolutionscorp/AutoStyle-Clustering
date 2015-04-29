class Bob
  
  # Bob is a lackadaisical teenager.
  # In conversation, his responses are very limited.
  #
  def method_missing(called, said)
    heard = said.to_s.extend TeenageListening

    case
    when heard.empty?
      # Bob says 'Fine. Be that way!' if you address him
      # without actually saying anything.
      'Fine. Be that way.'
    when heard.yelling?
      # He answers 'Woah, chill out!' if you yell at him (ALL CAPS).
      'Woah, chill out!'
    when heard.question?
      # Bob answers 'Sure.' if you ask him a question.
      'Sure.'
    when heard.statement?
      # He answers 'Whatever.' if you tell him something.
      'Whatever.'
    end
  end
  
  module TeenageListening
    
    def self.extended(a)
      raise 'Extending something other than a String' unless a.is_a? String
    end
    
    def question?
      self[-1] == '?'
    end
    
    def statement?
      not question?
    end
    
    def yelling?
      self == self.upcase
    end
    
  end
  
end

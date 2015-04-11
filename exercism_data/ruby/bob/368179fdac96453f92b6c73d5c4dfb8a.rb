require 'forwardable'

class Bob

  class Message
    extend Forwardable
    def_delegators :@msg, :==, :empty?, :upcase, :end_with?, :to_s
    
    def initialize(msg)
      @msg = msg || ""
    end
      
    def shouting?
      self == upcase
    end

    def asking_a_question?
      end_with? '?'
    end
  end

  def hey(message)
    m = Message.new(message)
    case
    when m.empty? then "Fine. Be that way."
    when m.shouting? then "Woah, chill out!"
    when m.asking_a_question? then "Sure."
    else "Whatever."
    end
  end

end

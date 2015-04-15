module Message
  # Message precedence is set by the order of symbols in this array
  TYPES = [:silence, :yell, :question, :default]

  TESTS = {
    :silence  => lambda{|msg| msg.to_s.strip.empty? },
    :yell     => lambda{|msg| msg.to_s.upcase == msg },
    :question => lambda{|msg| msg.to_s.strip.end_with?('?') },
    :default  => lambda{|msg| true }
  }

  def type_of(msg)
    TYPES.find{|t| TESTS[t].call(msg)}
  end
end

class Bob
  include Message
  
  RESPONSES = {
    :silence  => 'Fine. Be that way!',
    :yell     => 'Woah, chill out!',
    :question => 'Sure.',
    :default  => 'Whatever.'
  }
  
  def hey(msg)
    RESPONSES[type_of(msg)]
  end
end

# Message precedence is now set by the order of symbols in this array
MESSAGE_TYPES = [:silence, :yell, :question, :default]

MESSAGE_TESTS = {
  :silence  => lambda{|msg| msg.to_s.strip.empty? },
  :yell     => lambda{|msg| msg.to_s.upcase == msg },
  :question => lambda{|msg| msg.to_s.strip.end_with?('?') },
  :default  => lambda{|msg| true }
}

def msg_type(msg)
  MESSAGE_TYPES.find{|t| MESSAGE_TESTS[t].call(msg)}
end

class Bob
  RESPONSES = {
    :silence  => 'Fine. Be that way!',
    :yell     => 'Woah, chill out!',
    :question => 'Sure.',
    :default  => 'Whatever.'
  }
  
  def hey(msg)
    RESPONSES[msg_type(msg)]
  end
end

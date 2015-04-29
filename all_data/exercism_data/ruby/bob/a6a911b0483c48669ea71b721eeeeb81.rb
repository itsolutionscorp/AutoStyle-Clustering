class Response
  attr_accessor :test, :precedence, :phrase
  def initialize(precedence, test)
    @precedence = precedence
    @test = test
  end
end

GENERIC_RESPONSES = {
  :silence  => Response.new(100, lambda{|msg| msg.to_s.strip.empty? }),
  :yell     => Response.new(200, lambda{|msg| msg.to_s.upcase == msg }),
  :question => Response.new(300, lambda{|msg| msg.to_s.strip.end_with?('?') }),
  :default  => Response.new(400, lambda{|msg| true })
}

class Bob
  def initialize
    # Set Bob's personal responses to different classes of stimuli
    r = GENERIC_RESPONSES
    r[:silence].phrase  = 'Fine. Be that way!'
    r[:yell].phrase     = 'Woah, chill out!'
    r[:question].phrase = 'Sure.'
    r[:default].phrase  = 'Whatever.'
    
    # Sorting by precedence allows us to alter response order without
    # cutting and pasting code.
    @responses = r.values.sort_by{|a| a.precedence}
  end
  
  def hey(msg)
    @responses.find{|r| r.test.call(msg)}.phrase
  end
end

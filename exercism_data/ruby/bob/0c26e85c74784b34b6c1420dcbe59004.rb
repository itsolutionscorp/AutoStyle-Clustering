class Response
  attr_accessor :test, :precedence, :value
  def initialize(precedence, test)
    @precedence = precedence
    @test = test
  end
end

GENERIC_RESPONSES = {
  :silence  => Response.new(100, lambda{|msg| msg.to_s.strip == '' }),
  :yell     => Response.new(200, lambda{|msg| msg.to_s.upcase == msg }),
  :question => Response.new(300, lambda{|msg| msg.to_s.strip.end_with?('?') }),
  :default  => Response.new(400, lambda{|msg| true })
}

class Bob
  def initialize
    # Set Bob's personal responses to different classes of stimuli
    r = GENERIC_RESPONSES
    r[:silence].value  = 'Fine. Be that way!'
    r[:yell].value     = 'Woah, chill out!'
    r[:question].value = 'Sure.'
    r[:default].value  = 'Whatever.'
    
    # Sorting by precedence allows us to alter response order without
    # cutting and pasting code.
    @responses = r.values.sort{|a,b| a.precedence <=> b.precedence}
  end
  
	def hey(value)
    @responses.drop_while{|r| not r.test.call(value)}.first.value
	end
end

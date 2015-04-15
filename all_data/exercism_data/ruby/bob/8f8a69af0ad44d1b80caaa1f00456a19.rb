class Brain
  attr_reader :actions
  def initialize
    @actions = []
  end

  def add_answer(answer,test)
    actions << {answer: answer, test: test }
  end

  def think(remark)
    actions.find {|v| v[:test].call(remark) }[:answer]
  end
end

class Bob
  attr_reader :brain
  def initialize
    @brain = Brain.new
    
    brain.add_answer 'Whoa, chill out!', ->(s){ s.upcase == s && s.match(/[A-Z]/)}
    brain.add_answer 'Sure.',->(s){ s.end_with? '?' }
    brain.add_answer 'Fine. Be that way!', ->(s){ s.strip.empty?}
    brain.add_answer 'Whatever.', -> (s) { true }
  end

  def hey(remark)
    brain.think(remark)
  end

end

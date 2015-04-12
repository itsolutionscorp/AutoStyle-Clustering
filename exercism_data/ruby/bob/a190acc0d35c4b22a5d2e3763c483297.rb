# Normally, this would be in a separate file
module Strategies
  class Strategy
    attr_reader :response
    
    def initialize(test_proc, response)
      @test_proc = test_proc
      @response = response
    end
    
    def test(heard)
      @test_proc.call(heard)
    end
  end
  
  def default_strategies
    [silence?, shouting?, question?, anything?]
  end
  
  def silence?
    Strategy.new(->(heard) { heard.to_s.strip == "" }, "Fine. Be that way!")
  end
  
  def shouting?
    Strategy.new(->(heard) { heard == heard.upcase }, "Woah, chill out!"  )
  end
  
  def question?
    Strategy.new(->(heard) { heard.end_with?("?") }, "Sure.")
  end
  
  def anything?
    Strategy.new(->(heard) { true }, "Whatever.")
  end
end

class Bob
  attr_reader :strategies
  include Strategies
  
  def initialize(strategies = nil)
    @strategies = strategies || default_strategies
  end
  
  def hey(heard)
    strategies.find do |strategy|
      strategy.test(heard)
    end.response
  end
end
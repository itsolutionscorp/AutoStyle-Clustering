# This is the concatenation of 3 files
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
end
#require_relative "strategies"

class Responder
  attr_reader :strategies
  include Strategies
  
  def initialize(strategies = nil)
    @strategies = strategies || default_strategies
    @strategies << anything?
  end
  
  def hey(heard)
    strategies.find do |strategy|
      strategy.test(heard)
    end.response
  end
  
  private
  
  def default_strategies
    []
  end
  
  def anything?
    Strategy.new(->(heard) { true }, "")
  end
end
#require_relative "responder"

class Bob < Responder
  private
  
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

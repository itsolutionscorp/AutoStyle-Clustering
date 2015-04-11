class Robot

  attr_accessor :name
  
  def initialize
    @name = nil
  end

  def name
    reset if @name == nil
    @name
  end

  def reset
    irand = lambda { rand(10).to_s }
    crand = lambda { ('A'..'Z').to_a.sample }  
    @name = crand.call << crand.call << irand.call << 
            irand.call << irand.call
  end

end

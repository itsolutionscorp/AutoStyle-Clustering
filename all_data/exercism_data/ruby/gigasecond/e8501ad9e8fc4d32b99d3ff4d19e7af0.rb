class Gigasecond

  attr_accessor :gs

  def initialize(gs)
    @gs = gs 
  end
 
  def date()
    seconds_alive = @gs.to_time + (10**9)
    Time.at(seconds_alive).to_date
  end 

end

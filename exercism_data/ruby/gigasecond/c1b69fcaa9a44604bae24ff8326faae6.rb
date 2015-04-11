class Gigasecond
  def initialize(d)    
    # convert to days
    @d = d + ( 1_000_000_000 / 86_400 )
  end
  def date
    @d
  end
end

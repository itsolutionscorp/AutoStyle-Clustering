class Gigasecond
  attr_reader :gs

  def self.from(date)
    if date.class == Date
      @gs = date + (10**9)/60/60/24
    else 
      @gs = Date.parse((date + 10**9).to_s)
    end
  end
end

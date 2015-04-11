class Gigasecond
  def initialize(start_date)
    @start_date = start_date.to_date
  end

  def self.from(time)
    gigasec = (10**9/60/60/24) #in days

    (time + gigasec).to_date
  end
end

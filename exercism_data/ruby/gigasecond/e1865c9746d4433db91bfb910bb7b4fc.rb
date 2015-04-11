class Gigasecond
  def self.from(date)
    date + gigasecond
  end

  private

  def self.gigasecond
    10**9
  end
  
end

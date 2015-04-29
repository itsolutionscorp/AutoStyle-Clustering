class Gigasecond

  def self.from(date)
    (date + interval(date)).to_date
  end

  private

  def self.interval(date)
    if date.is_a?(Date)
      10**9 / (60 * 60 * 24)
    else 
      10**9
    end
  end
end

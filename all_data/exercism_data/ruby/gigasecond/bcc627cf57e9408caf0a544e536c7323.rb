class Gigasecond
  def self.from(start)
    Date.today + gs_in_days - days_since(start)
  end

  private

  def self.days_since(start)
    Date.today - start
  end

  def self.gs_in_days
    1_000_000_000 / 60 / 60 / 24
  end
end

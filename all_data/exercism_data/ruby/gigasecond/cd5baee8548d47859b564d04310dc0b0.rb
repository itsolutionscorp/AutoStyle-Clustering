class Gigasecond
  def self.from(start)
    start + gs_in_days
  end

  private

  def self.gs_in_days
    1_000_000_000 / 60 / 60 / 24
  end
end

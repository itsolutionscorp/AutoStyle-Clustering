class Gigasecond
  def initialize born_at
    @born_at = born_at.to_time
  end
  def date
    (@born_at+1_000_000_000).to_date
  end
end

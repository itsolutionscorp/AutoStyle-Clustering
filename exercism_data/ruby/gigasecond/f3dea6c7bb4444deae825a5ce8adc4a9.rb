class Gigasecond
  def self.calculate_gigasecond_in_days
    (10**9)/60/60/24
  end

  def self.gigasecond_in_days
    @gs_in_days ||= calculate_gigasecond_in_days
  end

  def self.from(date)
    date + gigasecond_in_days
  end
end

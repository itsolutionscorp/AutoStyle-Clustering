class Gigasecond
  def self.from(date)
  	self.next_gs(date)
  end

  private

  def self.next_gs(from_date)
    next_gs_epoch = self.seconds_since_epoch(from_date) + 10 ** 9
    DateTime.strptime(next_gs_epoch.to_s, '%s').to_date
  end

  def self.seconds_since_epoch(date)
    date.strftime('%s').to_i
  end
end

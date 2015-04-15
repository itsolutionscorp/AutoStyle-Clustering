class Gigasecond
  def self.from(birthday)
    gs_epoch = birthday.to_time + ( 10**9 )
    gs_epoch.to_date
  end
end

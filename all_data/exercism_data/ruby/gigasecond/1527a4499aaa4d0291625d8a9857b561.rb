class Gigasecond
  require 'date'
  require 'time'

  def self.from(date)
    add_gs(date).to_date
  end
  
  def self.add_gs(date)
    date.to_time + 10**9
  end
end

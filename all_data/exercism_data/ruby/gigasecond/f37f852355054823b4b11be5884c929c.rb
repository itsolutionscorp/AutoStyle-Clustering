class Gigasecond
  def self.from(date) 
    DateTime.strptime("#{date.to_time.to_i + (10**9)}", '%s').to_date
  end
end

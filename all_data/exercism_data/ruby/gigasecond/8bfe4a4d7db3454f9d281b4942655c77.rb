class Gigasecond
  def self.from(initial_date)
    # Convert 10**9 seconds to days and add to initial date
    initial_date + (10**9/86400)
  end
end

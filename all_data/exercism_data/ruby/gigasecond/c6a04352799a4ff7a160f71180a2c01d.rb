require 'date'

class Gigasecond
  def self.from date
    seconds_to_date seconds(date) + giga
  end

  private

  def self.giga
    10**9
  end

  def self.epoch_time
    Date.new(1970, 1, 1)
  end

  def self.seconds date
    date.strftime("%s").to_i
  end

  def self.seconds_to_date epoch
    Date.strptime(epoch.to_s, "%s")
  end
end

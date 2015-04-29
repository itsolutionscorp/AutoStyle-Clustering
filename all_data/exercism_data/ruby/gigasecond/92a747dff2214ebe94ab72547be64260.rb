#!/usr/bin/env ruby

# Calculate when a person will reach their gigabirth (10**9 seconds)

class Gigasecond

  def self.from(date_obj)

    from_epoch_secs = date_obj.to_time.utc.to_i
    gigabirth = Date.parse(Time.at(from_epoch_secs + (10**9)).to_s)

  end

end

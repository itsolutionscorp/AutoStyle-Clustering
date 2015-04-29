require 'date'
require 'time'

class Gigasecond

  def self.from(gs)
    date_in_sec = gs.to_time.to_i
    gs_birtday_in_sec = date_in_sec + 10**9
    date_of_gs_birthday = Time.at(gs_birtday_in_sec).to_date
    return date_of_gs_birthday
  end
end

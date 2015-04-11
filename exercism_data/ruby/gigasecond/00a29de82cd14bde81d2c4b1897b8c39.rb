require 'date'
require 'time'

class Gigasecond

  def self.from(date)

    gs = (10**9)
    sec_per_day = (60*60*24)
    gs_days = gs/sec_per_day

    if date.is_a?(Date)

      result = date + gs_days

    elsif date.is_a?(Time)


      sec_per_hour = (60*60)
      sec = 60

      gs_hours = (gs_days%sec_per_day)/sec_per_hour
      gs_min = ((gs_days%sec_per_day)%sec_per_hour)/sec
      gs_sec = ((gs_days%sec_per_day)%sec_per_hour)%sec

      if date.hour >= gs_hours && date.min >= gs_min && date.sec >= gs_sec

        result = Date.new(date.year, date.month, date.day) + gs_days + 1

      else

        result = Date.new(date.year, date.month, date.day) + gs_days

      end

    end

    result

  end

end

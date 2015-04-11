class Meetup < Struct.new(:month, :year)

  SCHEDULES = {
    first:  [*1..7],
    second: [*8..15],
    third:  [*15..23],
    fourth: [*22..31],
    last:   [*1..31].reverse,
    teenth: [*13..19],
  }

  def day weekday, schedule
    raise "Unknown schedule : '#{schedule}'" unless SCHEDULES.key? schedule
    day = SCHEDULES[schedule].find do |d|
      begin
        Date.new(year, month, d).send("#{weekday}?")
      rescue
        false
      end
    end
    Date.new(year, month, day)
  end

end

class Gigasecond

  GIGASECONDS = (10**9)
  DAY_SECONDS = 86400

  def self.from(date_input)
    if date_input.is_a?(Time)
      date_input = DateTime.parse(date_input.to_s)
      gs_date_time = date_input + (GIGASECONDS.to_f / DAY_SECONDS.to_f)
      gs_date_time.to_date
    else
      date_input + (GIGASECONDS / DAY_SECONDS)
    end
  end
end

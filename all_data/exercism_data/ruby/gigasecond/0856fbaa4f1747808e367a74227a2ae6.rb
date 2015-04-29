class Gigasecond
  def self.from(in_date)

    if in_date.is_a?(Date)
      in_date_as_time = Time.new(in_date.year,in_date.month,in_date.day)
    else
      in_date_as_time = in_date
    end

    new_date = in_date_as_time + 1e9
    Date.new(new_date.year, new_date.month, new_date.day)
  end
end

class Gigasecond
  def self.gigasecond
    @gigasecond = 10 ** 9
  end

  def self.from(birthday)
    birthday + gigasecond
  end                      # => :from
end                        # => :from

# Gigasecond.from(Time.utc(1987, 12, 30))

# gigasecond = 10 ** 9  # => 1000000000

# seconds_per_day = 86400  # => 86400

# days_in_gigasecond = gigasecond / seconds_per_day  # => 11574

# days_in_year = 365  # => 365

# years_in_gigasecond = days_in_gigasecond / days_in_year  # => 31

# seconds_in_year = seconds_per_day * days_in_year  # => 31536000


# years_in_gigasecond == gigasecond / seconds_in_year  # => true

# gs_from = Time.utc(2011, 4, 25)  # => 2011-04-25 00:00:00 UTC

# gs_bd = gs_from + gigasecond  # => 2043-01-01 01:46:40 UTC

# gs_bd  # => 2043-01-01 01:46:40 UTC

class Gigasecond

  @gigasec = 10**9                             # 1 billion seconds constant

  def self.from(rawdate)                       # raw input date
    dateseconds = rawdate.strftime('%s').to_i  # convert date to seconds integer
    dateseconds = dateseconds + @gigasec       # explicitly add 1 billion secs
    Date.strptime("#{dateseconds}", '%s')      # reconvert back Date format
  end

end

class Gigasecond

  def self.from(date_arg)
    if date_arg.class == Date
      date_arg += Rational(10**9, 86400)
    elsif date_arg.class == Time
      date_arg += 10**9
    else
      abort date_arg.class.to_s
    end
    Date.new(date_arg.year, date_arg.month, date_arg.day)
  end
end

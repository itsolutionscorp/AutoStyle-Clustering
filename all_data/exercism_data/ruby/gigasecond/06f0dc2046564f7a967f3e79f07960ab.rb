class Gigasecond
  def self.from (arg)
    if arg.class == Date
      arg + (10**9/86400)
    else
      (arg + (10**9)).to_date
    end
  end
end

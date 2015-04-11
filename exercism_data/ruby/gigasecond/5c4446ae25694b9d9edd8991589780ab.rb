class Gigasecond
  def self.from(date)
    from = date.to_time.to_i
    to   = from + (10 ** 9)
    Date.strptime(to.to_s, "%s").next
  end
end

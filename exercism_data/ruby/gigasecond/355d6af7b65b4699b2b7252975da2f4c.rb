class Gigasecond
  def self.from(date)
    t_from = date.to_i
    result = Time.at(t_from + 10**9)
  end
end

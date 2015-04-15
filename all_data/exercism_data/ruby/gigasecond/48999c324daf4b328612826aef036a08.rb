class Gigasecond
  # 10**9 seconds
  def self.from(start)
    if start.is_a? Date
      start + 11574
    else
      start.to_date + 11575
    end
  end
end

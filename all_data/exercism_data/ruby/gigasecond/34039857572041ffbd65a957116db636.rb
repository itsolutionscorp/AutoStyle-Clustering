class Gigasecond
  def self.from (date)
    gst = date.to_time + 10**9
    Date.strptime(gst.to_s, '%Y-%m-%d %T %Z')
  end
end

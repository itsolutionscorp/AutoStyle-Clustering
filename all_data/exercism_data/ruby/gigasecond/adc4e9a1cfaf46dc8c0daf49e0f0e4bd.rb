class Gigasecond
  def self.from(date)
    gs_seconds = date.to_i + 10**9 + (date.gmt_offset - Time.now.gmt_offset)
    Time.at(gs_seconds).dst? ? Time.at(gs_seconds - 3600) : Time.at(gs_seconds)
  end
end

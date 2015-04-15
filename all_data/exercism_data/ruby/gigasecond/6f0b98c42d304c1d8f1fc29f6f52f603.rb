class Gigasecond
  def self.from(time)
    current_secs=time.to_i;
    secs_after_gs=current_secs+10**9;
    time_after_gs=Time.at(secs_after_gs)
    return time_after_gs;
  end
end

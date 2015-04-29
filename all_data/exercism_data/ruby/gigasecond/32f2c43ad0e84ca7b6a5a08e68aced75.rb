class Gigasecond

  def self.from initial
    time = initial.kind_of?(Date) ? Time.parse(initial.to_s) : initial
    time = time + 10**9
    Date.parse(time.to_s)
  end

end

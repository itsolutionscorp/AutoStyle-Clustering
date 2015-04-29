# Calculate the date 1 billion seconds from when specified
class Gigasecond
  def self.from(d)
    fail(ArgumentError, "You must provide a Date object (or any of it's descendants)") unless d.is_a?(Date)
    (d.to_time + 10**9).to_date
  end
end

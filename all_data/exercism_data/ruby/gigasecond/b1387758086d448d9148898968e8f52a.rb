class Gigasecond
  def self.from input
    case input
    when Date, DateTime
      input + 11574 # 1 gigasecond in days
    when Time
      (input + 1_000_000_000).to_date
    end
  end
end

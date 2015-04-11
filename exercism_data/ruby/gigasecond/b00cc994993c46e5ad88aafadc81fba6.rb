class Gigasecond
  def self.from *args
    new *args
  end

  def initialize from
    @from = from.to_time
  end

  def date
    (@from + 1_000_000_000).to_date
  end

  attr_accessor :from
end

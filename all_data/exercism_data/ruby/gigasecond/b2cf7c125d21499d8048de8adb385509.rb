class Gigasecond
  def initialize date
    @date = date + (1e9 / (24*3600.0)).floor
  end

  attr_reader :date
end

# class for Gigaseconds
class Gigasecond
  attr_reader :date

  def self.from( base_date )
    new( base_date )
  end

  def initialize( base )
    @date = (base.to_time + 1_000_000_000).to_date
  end
end

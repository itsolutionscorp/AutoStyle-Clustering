GIGASECONDS=10**9

module Gigasecond
  def self.from(date)
    if date.respond_to? :to_time
      (date.to_time + GIGASECONDS).to_date
    else
      raise ArgumentError.new("`date` needs to respond to :to_time!")
    end
  end
end

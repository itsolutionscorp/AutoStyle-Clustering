class Gigasecond

  def self.from date
    raise ArgumentError, 'date must be of type Date' unless date.is_a? Date    
    Time.at(date.to_time.to_i + 1000000000).to_date
  end

end

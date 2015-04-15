class Gigasecond 
  def self.from(date) 
    start_time = date.to_time.to_i
    end_time = ( start_time + (10**9) )
    return Time.at(end_time).to_date
  end 
end

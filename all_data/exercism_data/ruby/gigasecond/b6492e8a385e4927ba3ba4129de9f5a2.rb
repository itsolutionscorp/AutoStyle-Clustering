class Gigasecond
  def self.from(dt)
    epoc = if dt.instance_of? Date
      dt.strftime('%s').to_i + 1000000000 + 24*60*60
    else
      dt.strftime('%s').to_i + 1000000000
    end
    Time.at(epoc).to_date
  end
end

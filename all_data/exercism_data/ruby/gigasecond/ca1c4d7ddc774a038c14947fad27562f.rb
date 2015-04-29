class Gigasecond
  def self.from(d)
    return Time.at(d.strftime('%s').to_i + 10**9).to_date
  end
end

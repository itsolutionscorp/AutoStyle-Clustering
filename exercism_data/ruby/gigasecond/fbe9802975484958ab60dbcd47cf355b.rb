class Gigasecond < Struct.new(:birthday)
  def date
    Time.at(birthday.to_time.to_i + 1E9).to_date
  end
end

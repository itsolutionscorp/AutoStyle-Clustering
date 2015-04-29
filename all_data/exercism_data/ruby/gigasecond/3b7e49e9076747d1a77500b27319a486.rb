class Gigasecond < Struct.new(:birthday)
  def date
    (birthday.to_time + 1E9).to_date
  end
end

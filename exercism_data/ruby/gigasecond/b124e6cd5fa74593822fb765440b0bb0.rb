class Gigasecond
  def self.from date
    time_after_gs = create_time_object_from(date) + 1000000000
    create_date_object_from(time_after_gs)
  end

  def self.create_time_object_from date
    date.to_time
  end

  def self.create_date_object_from time
    time.to_date
  end
end

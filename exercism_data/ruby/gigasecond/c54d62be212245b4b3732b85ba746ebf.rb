class Gigasecond
  def self.from some_date
    # puts "some date :#{some_date.class}"
    one_g_anaversery = some_date.to_time + (10**9)

    one_g_anaversery.to_date
  end
end

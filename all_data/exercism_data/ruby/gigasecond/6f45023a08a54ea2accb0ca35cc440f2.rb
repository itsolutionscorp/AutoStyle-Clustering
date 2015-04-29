class Gigasecond

  def self.from(start_date)

    gigasecond = 1000000000
    seconds_in_one_day = 86400


    start_date. + (gigasecond / seconds_in_one_day)


  end
end

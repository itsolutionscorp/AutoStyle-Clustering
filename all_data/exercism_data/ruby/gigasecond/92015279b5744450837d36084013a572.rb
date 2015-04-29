class Gigasecond
  GIGASECOND = 10 ** 9

  def self.from(birthday)
    #
    # "self" above is necessary to create a class method?
    #
    (birthday.to_time + GIGASECOND).to_date
  end
end

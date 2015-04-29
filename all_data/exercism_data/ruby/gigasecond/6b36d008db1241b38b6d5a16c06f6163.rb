class Gigasecond < Struct.new(:birthday)
  GIGASECONDS = 10**9

  def date
    date_of birthday_in_epoch + GIGASECONDS
  end

  private

  def birthday_in_epoch
    birthday.to_time.to_i
  end

  def date_of(epoch_time)
    Time.at(epoch_time).to_date
  end
end

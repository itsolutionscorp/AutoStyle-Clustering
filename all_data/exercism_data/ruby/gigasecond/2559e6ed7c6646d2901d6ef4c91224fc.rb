require 'date'
require 'time'

class Gigasecond
  attr_reader :birthday

  def initialize(birthday)
    @birthday = birthday
  end

  # Return the date where this person will be 10**9 seconds old
  def date
    unix_epoch_birthday   = birthday.strftime('%s')
    gigasecond_unix_epoch = unix_epoch_birthday.to_i + 10**9

    Time.at(gigasecond_unix_epoch).to_date
  end
end

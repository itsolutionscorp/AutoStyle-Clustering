require 'date'

class Gigasecond
  def self.from(birthDay)
      bd = (1000000000 + Time.new(birthDay.year,birthDay.month,birthDay.mday).to_i).to_s
      return DateTime.strptime(bd, '%s').to_date
  end
end

class Year
  def self.leap? year
    return (year % 400).zero? if (year % 100).zero?
    (year % 4).zero?
  end
end

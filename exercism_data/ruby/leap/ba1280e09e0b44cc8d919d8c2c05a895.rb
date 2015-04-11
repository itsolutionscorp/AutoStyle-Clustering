class Year
  def self.leap?(year)
    case
    when (year % 400).zero?
      true
    when ((year % 4).zero? and (not (year % 100).zero?))
      true
    else
      false
    end
  end
end

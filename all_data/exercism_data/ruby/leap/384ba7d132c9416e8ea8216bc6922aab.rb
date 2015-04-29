class Year

  # leap year rules
  #   1) Add an extra day every 4 years
  #   2) Skip it if it's a new century (i.e. 1900)
  #   3) Unless the century is divisible by 400

  def self.leap?(year)

    if year % 400 == 0
      true

    elsif year % 100 == 0
      false

    elsif year % 4 == 0
      true

    else
      false

    end
  end

end

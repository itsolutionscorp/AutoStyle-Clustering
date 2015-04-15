class Year

  def self.leap?(year)
    leap = false

    if year % 4 == 0
      leap = true
    end

    if year % 100 == 0
      leap = false
    end

    if year % 400 == 0
      leap = true
    end

    return leap

  end

end

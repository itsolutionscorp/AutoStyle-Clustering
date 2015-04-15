class Year
  def self.leap?(year)
    if (year / 100.0) == (year / 100)
      return (year / 400.0) == (year / 400)
    end

    (year / 4.0) == (year / 4)
  end
end

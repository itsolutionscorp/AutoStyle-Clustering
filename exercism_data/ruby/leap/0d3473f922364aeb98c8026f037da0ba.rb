class Year

  def self.leap?(year)
    if self.by_100?(year)
      self.by_400?(year) ? true : false
    else
      self.by_4?(year)
    end
  end

  def self.by_4?(year)
    ((year / 4.0) % 1 == 0) ? true : false
  end

  def self.by_100?(year)
    ((year / 100.0) % 1 == 0) ? true : false
  end

  def self.by_400?(year)
    ((year / 400.0) % 1 == 0) ? true : false
  end
end

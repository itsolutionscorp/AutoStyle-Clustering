class Year
  def self.leap?(n)
    return true if n % 400 == 0
    return false if n % 100 == 0
    n % 4 == 0
  end
end

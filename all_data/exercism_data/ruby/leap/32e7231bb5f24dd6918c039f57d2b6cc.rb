class Year
  def self.leap?(n)
    if n % 4 == 0
      return n % 400 == 0 if n % 100 == 0
      true
    else
      false
    end
  end
end

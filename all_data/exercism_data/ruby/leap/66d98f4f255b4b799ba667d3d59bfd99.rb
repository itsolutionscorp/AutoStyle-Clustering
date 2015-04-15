class Year
  def self.leap?(year)
    multiple?(year, 4) || multiple?(year, 400)
  end

  private
  def self.multiple?(year, other)
    year.gcd(400).eql?(other)
  end
end

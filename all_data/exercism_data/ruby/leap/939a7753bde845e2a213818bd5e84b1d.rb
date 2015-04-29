class Year
  def self.leap?(n)
    (n % 4).zero? && ((n % 400).zero? || (n % 100).nonzero?)
  end
end

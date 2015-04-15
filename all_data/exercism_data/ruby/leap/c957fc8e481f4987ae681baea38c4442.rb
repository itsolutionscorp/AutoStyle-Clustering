class Year
  def self.leap?(i)
    (i % 4) == 0 and ((i % 400) == 0 or (i % 100) != 0)
  end
end

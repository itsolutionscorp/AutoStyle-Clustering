class Year
  def self.leap?(yy)
    (yy % 4 == 0) &&
      ((yy % 400 == 0) ||
      (yy % 100 != 0))
  end
end

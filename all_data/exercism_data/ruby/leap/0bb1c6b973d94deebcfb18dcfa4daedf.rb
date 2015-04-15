class Year
  def self.leap? value
    value % 400 == 0 || value % 4 == 0 && value % 100 != 0
  end
end

class Year
  def self.leap? year
    parts = year.divmod(100)
    parts[1]==0 && parts[0]%4!=0 || parts[1]%4!=0 ? false : true
  end
end

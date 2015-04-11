class Year
  def self.leap? yaer
    if yaer % 100 == 0 && yaer % 400 == 0
      return true
    elsif yaer % 100 == 0
      false
    elsif yaer % 4 == 0
      true
    end
  end
end

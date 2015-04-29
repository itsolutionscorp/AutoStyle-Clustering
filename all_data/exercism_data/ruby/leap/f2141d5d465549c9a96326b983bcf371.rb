class Year

  def self.leap?(year)
    if year.to_i % 400 == 0
      true
    elsif year.to_i % 100 == 0
      false
    elsif year.to_i % 4 == 0
      true
    end
  end
end

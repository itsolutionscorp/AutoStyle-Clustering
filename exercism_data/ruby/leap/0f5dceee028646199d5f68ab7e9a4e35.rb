module Year
  
  def self.leap?(year)
    false
    if year.modulo(4).zero?
      if year.modulo(100).zero?
        year.modulo(400).zero?
      else
        true
      end
    end
  end
end

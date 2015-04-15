class Year

  def self.leap? y
    if (y % 4 == 0) && ( !(y % 100 == 0) || (y % 400 == 0))
      true
    else
      false
    end
  end

end

puts Year.leap?('masło')

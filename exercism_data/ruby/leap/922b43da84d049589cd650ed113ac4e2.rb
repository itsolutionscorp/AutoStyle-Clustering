class Year

  def self.leap?(yearIn)
    ((yearIn%4).zero? && !(yearIn%100).zero?) || ((yearIn%400).zero? && (yearIn%100).zero?)
  end

end

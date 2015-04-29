class Year
  def self.leap? year
    (year%4).zero? and ( not (year%100).zero? or (year%400).zero?)
  end
end

# override ruby's own leap? method
class Year
  def self.leap?(year)
    [[true, false, false],[true, true, true]].include? [4, 100, 400].map { |n| year % n == 0 }
  end
end

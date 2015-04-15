# year.rb

class Year

  def self.leap? year
    return true  if 0 == year % 400
    return false if 0 == year % 100
    return true  if 0 == year % 4
    false
  end

end

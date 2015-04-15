module Raindrops

  def self.div_3_string(number)
    (number % 3 == 0)? 'Pling' : ''
  end

  def self.div_5_string(number)
    (number % 5 == 0)? 'Plang' : ''
  end

  def self.div_7_string(number)
    (number % 7 == 0)? 'Plong' : ''
  end

  def self.string(number)
    self.div_3_string(number) + self.div_5_string(number) + self.div_7_string(number)
  end

  def self.convert(number)
    (self.string(number).empty?)? number.to_s : self.string(number)
  end

end

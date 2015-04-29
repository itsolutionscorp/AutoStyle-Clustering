module Raindrops
  def self.convert(num)
    if !(num % 3 == 0 || num % 5 == 0 || num % 7 == 0)
      num.to_s
    else
      self.mod_3(num)
    end
  end

  def self.mod_3(num)
    if num % 3 == 0
      'Pling'+ self.mod_5(num).to_s
    else
      self.mod_5(num)
    end
  end

  def self.mod_5(num)
    if num % 5 == 0
      'Plang' + self.mod_7(num).to_s
    else
      self.mod_7(num)
    end
  end

  def self.mod_7(num)
    if num % 7 == 0
      'Plong'
    end
  end
end

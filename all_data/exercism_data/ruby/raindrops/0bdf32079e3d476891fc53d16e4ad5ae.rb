module Raindrops
  class << self
    def convert(num)
      string = ""
      string += "Pling" if num % 3 == 0
      string += "Plang" if num % 5 == 0
      string += "Plong" if num % 7 == 0
      if string.empty?
        num.to_s
      else
        string
      end
    end
  end
end

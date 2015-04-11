module Raindrops

  class << self

    def convert n
      words = ""
      words = "Pling" if n%3 == 0
      words += "Plang" if n%5 == 0
      words += "Plong" if n%7 == 0
      words = "#{n}" if words == ""
      words
    end
  end
end

require 'prime.rb'

class Raindrops
  class << self
    def convert(n)
      temp = String.new
      temp += "#{"Pling" if (n%3).eql?(0)}#{"Plang" if (n%5).eql?(0)}#{"Plong" if (n%7).eql?(0)}"
      temp.eql?("") ? temp = n.to_s : temp
    end
  end
end

require 'prime'

class Raindrops
  class << self
    def convert n
      factors = n.prime_division.flatten
      str = ''
      str += 'Pling' if factors.member? 3
      str += 'Plang' if factors.member? 5
      str += 'Plong' if factors.member? 7
      str.empty? ? n.to_s : str
    end
  end
end

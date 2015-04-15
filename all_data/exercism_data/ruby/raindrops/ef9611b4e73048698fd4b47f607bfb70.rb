require 'prime'

class Raindrops
  def self.convert(n)
    str = ''
    n.prime_division.each do |factor|
      if factor[0] == 3
        str += 'Pling'
      end
      if factor[0] == 5
        str += 'Plang'
      end
      if factor[0] == 7
        str += 'Plong'
      end
    end
    str = n.to_s if str.empty?
    str
  end
end

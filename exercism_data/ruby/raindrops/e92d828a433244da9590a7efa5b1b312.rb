require 'prime'

class Raindrops
  def self.convert(n)
    s = (n % 3 == 0 ? 'Pling' : '') + (n % 5 == 0 ? 'Plang' : '') + (n % 7 == 0 ? 'Plong' : '')
    s == '' ? n.to_s : s
  end
end

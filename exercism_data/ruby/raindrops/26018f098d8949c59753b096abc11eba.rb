require 'prime'

class Raindrops
  def self.convert(drops)
    str = ''
    str << 'Pling' if drops % 3 == 0
    str << 'Plang' if drops % 5 == 0
    str << 'Plong' if drops % 7 == 0
    str = drops.to_s if str.empty?
    str
  end
end

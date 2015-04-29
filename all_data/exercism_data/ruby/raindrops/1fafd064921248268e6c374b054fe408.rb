# raindrops.rb

module Raindrops
  def self.convert n
    s = ''
    s << 'Pling' if 0 == n % 3
    s << 'Plang' if 0 == n % 5
    s << 'Plong' if 0 == n % 7
    s.empty? ? n.to_s : s
  end
end

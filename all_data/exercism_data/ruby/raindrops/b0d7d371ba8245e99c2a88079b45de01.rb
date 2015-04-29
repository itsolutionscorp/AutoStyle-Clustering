require 'Prime'

class Raindrops
  def self.convert(n)
    r = ''
    s = {3 => 'Pling', 5 => 'Plang', 7 => 'Plong'}
    factors = Prime.prime_division(n)
    factors.each do |f|
      f.each do |c|
        r += ( s[c].nil? ? '' : s[c] )
      end
    end
    return ( r.length > 0 ? r : n.to_s )
  end
end

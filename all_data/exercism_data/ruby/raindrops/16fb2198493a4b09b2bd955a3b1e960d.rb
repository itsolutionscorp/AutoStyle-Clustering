# exercism - ruby/raindrops
class Raindrops
  DROPS = {
    3 => 'Pling',
    5 => 'Plang',
    7 => 'Plong'
  }

  def self.convert(n)
    plxng = proc { |mod, str| str if n % mod == 0 }
    result = DROPS.map(&plxng).join
    result != '' ? result : "#{n}"
  end
end

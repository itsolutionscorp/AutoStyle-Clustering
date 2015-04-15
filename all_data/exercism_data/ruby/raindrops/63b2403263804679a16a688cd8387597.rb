class Raindrops

  MAP = Hash.new('').merge({3 => 'i', 5 => 'a', 7 => 'o'})

  def self.convert(no_of_drops)
    string = MAP.keys.collect {|n| "Pl#{MAP[n]}ng" if no_of_drops%n == 0}.join
    string.empty? ? no_of_drops.to_s : string
  end
end

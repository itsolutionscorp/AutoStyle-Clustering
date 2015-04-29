class Raindrops
  def self.convert(n)
    conversion = {
      3 => 'Pling',
      5 => 'Plang',
      7 => 'Plong'
    }

    ary = conversion.keys.reduce([]) do |a, e|
      a << conversion[e] if (n % e).zero?
      a
    end
    ary.empty? ? n.to_s : ary.join
  end
end

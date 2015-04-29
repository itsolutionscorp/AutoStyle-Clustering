class Raindrops
  def self.convert(n)
    conversion = {
      3 => 'Pling',
      5 => 'Plang',
      7 => 'Plong'
    }

    ary = conversion.reduce([]) { |a, (k, v)| (n % k).zero? ? a << v : a }
    ary.empty? ? n.to_s : ary.join
  end
end

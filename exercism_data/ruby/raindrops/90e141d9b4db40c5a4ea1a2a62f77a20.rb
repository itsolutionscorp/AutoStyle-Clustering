class Raindrops
  def self.convert(n)
    conversion = {
      3 => 'Pling',
      5 => 'Plang',
      7 => 'Plong'
    }

    ary = conversion.reduce([]) do |a, (k, v)|
      a << v if (n % k).zero?
      a
    end
    ary.empty? ? n.to_s : ary.join
  end
end

class Raindrops
  TABLE = {3 => 'Pling', 5  => 'Plang', 7 => 'Plong'}
  def self.convert(x)
    result = TABLE.map do |k, v|
      if x % k == 0
        v
      end
    end.compact.join

    if result.empty?
      x.to_s
    else
      result
    end
  end
end

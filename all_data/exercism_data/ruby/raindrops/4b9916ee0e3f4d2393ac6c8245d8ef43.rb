class Raindrops
  TABLE = {3 => 'Pling', 5  => 'Plang', 7 => 'Plong'}
  def self.convert(x)
    result = TABLE.map do |k, v|
      v if x % k == 0
    end.compact.join

    result.empty? ? x.to_s : result
  end
end

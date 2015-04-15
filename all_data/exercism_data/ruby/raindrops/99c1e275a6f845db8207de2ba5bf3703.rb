class Raindrops
  def self.convert(num)
    res = ''
    res << 'Pling' if num.modulo(3).zero?
    res << 'Plang' if num.modulo(5).zero?
    res << 'Plong' if num.modulo(7).zero?
    res << num.to_s if res.empty?
    res
  end
end

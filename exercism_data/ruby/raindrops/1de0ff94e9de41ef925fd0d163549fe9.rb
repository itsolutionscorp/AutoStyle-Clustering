class Raindrops
  def self.convert(num)
    str = ''
    str << 'Pling' if (num % 3).zero?
    str << 'Plang' if (num % 5).zero?
    str << 'Plong' if (num % 7).zero?
    str << num.to_s if str.empty?
    str
  end
end

class Raindrops
  RAINMAP = {3 => 'Pling', 5 => 'Plang', 7 => 'Plong'}

  def self.convert(num, string = '')
    RAINMAP.each { |prime, name| string << name if (num % prime == 0) }
    string.size == 0 ? num.to_s : string
  end
end

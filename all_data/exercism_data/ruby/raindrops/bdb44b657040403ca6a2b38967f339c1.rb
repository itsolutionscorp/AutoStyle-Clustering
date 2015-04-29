class Raindrops
  class << self
    def convert(num)
      [{ 3 => 'Pling', 5 => 'Plang', 7 => 'Plong' }.select { |k| num % k == 0 }.values.join,num.to_s].max
    end
  end
end

class Raindrops
  class << self
    def convert(num)
      [sounds.select { |k| num % k == 0 }.values.join,num.to_s].max
    end

    private
    def sounds
      { 3 => 'Pling', 5 => 'Plang', 7 => 'Plong' }
    end
  end
end

class Raindrops
  class << self
    def convert(num)
      [choose_sounds(num),num.to_s].max
    end

    private
    def sounds
      { 3 => 'Pling', 5 => 'Plang', 7 => 'Plong' }
    end

    def choose_keys(num)
      sounds.select { |k| num % k == 0 }
    end

    def choose_sounds(num)
      choose_keys(num).values.join
    end
  end
end

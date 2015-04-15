class Raindrops
  class << self
    def convert(num)
      performance_test=SOUNDS.select { |k| num % k == 0 }
      performance_test.empty? ? num.to_s : performance_test.values.join
    end

    private
    SOUNDS={3 => 'Pling',5 => 'Plang',7 => 'Plong'}
  end
end

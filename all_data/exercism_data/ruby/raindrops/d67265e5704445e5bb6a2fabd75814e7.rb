class Raindrops
  PRIME_MAP = { 3 => 'Pling', 5 => 'Plang', 7 => 'Plong' }

  class << self
    def convert(n)
      (conversion = raindrop_speak(n)).empty? ? n.to_s : conversion
    end

    private
      PRIME_MAP.each do |prime, word|
        define_method("divisible_by_#{prime}?") { |n| n % prime == 0 ? word : '' }
      end

      def raindrop_speak(n)
        PRIME_MAP.keys.collect { |prime| send("divisible_by_#{prime}?", n) }.join
      end
  end
end

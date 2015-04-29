class Raindrops

  def self.convert(number, rules = DefaultRules.new)
    output = rules.apply(number.to_i)
    default_if_empty(output, number.to_s)
  end

  def self.default_if_empty(output, default)
    output.empty? ? default : output
  end

  class DefaultRules

    def initialize
      @rules = {
        divisible_by(3) => 'Pling',
        divisible_by(5) => 'Plang',
        divisible_by(7) => 'Plong'
      }
    end

    def apply(number)
      rules.map {|rule, output|
        output if applies?(rule, number)
      }.join
    end

    private

    attr_reader :rules

    def applies?(rule, number)
      rule.call(number)
    end

    def divisible_by(factor)
      ->(number) { number % factor == 0}
    end

  end

end

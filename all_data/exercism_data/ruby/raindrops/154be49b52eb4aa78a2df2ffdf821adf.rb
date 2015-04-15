module Raindrops
  class Check < Struct.new(:divisor, :response)
    def call(num)
      response if num % divisor == 0
    end
  end

  CHECKS =
    [Check.new(3, "Pling"),
     Check.new(5, "Plang"),
     Check.new(7, "Plong")]

  def self.convert(num)
    outputs = CHECKS.map { |check| check.call(num) }.compact
    return outputs.join unless outputs.empty?
    num.to_s
  end
end

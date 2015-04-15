require 'prime'

class Raindrops

  @conversion = {
    3 => 'Pling',
    5 => 'Plang',
    7 => 'Plong'
  }

  def initialize
    attr_reader :conversion
  end

  def self.convert(num)
    result = []
    n = Prime.prime_division(num).flatten.select {|n| n > 1}
    n.each do |prime|
      check = @conversion[prime]
      result.push(check)
    end
    result = result.delete_if { |item| item == nil }.join
    result.empty? ? num.to_s : result
  end
end

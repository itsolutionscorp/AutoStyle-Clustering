require 'prime'

module Raindrops
  def self.convert(number)
    @phrase = ''

    Prime.prime_division(number).flatten.sort.uniq.each do |prime_number|
      case (prime_number)
      when 3
        @phrase << 'Pling'
      when 5
        @phrase << 'Plang'
      when 7
        @phrase << 'Plong'
      end
    end

    if @phrase == ''
      return number.to_s
    else
      return @phrase
    end
  end
end

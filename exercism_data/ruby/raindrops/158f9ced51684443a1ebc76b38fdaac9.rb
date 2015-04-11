require 'prime'

class Raindrops
  @@subs = { '3' => 'Pling', '5' => 'Plang', '7' => 'Plong' }

  def self.convert(num)
    raindrop = num.prime_division.inject('') do |result, factor|
      result << @@subs.fetch(factor.first.to_s, '')
      result
    end

    raindrop.empty? ? num.to_s : raindrop
  end
end

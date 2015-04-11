require 'prime'

class Raindrops
  @@translation = { '3' => 'Pling', '5' => 'Plang', '7' => 'Plong' }

  def self.convert(num)
    factors = Prime.prime_division(num).flatten.uniq.map(&:to_s)

    conversion = factors.reduce('') do |memo, factor|
      drop = @@translation[factor]
      memo << drop unless drop.nil?
      memo
    end
    return num.to_s if conversion.empty?
    conversion
  end
end

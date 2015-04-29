require 'prime'

class Raindrops
  @@translation = { 3 => 'Pling', 5 => 'Plang', 7 => 'Plong' }

  def self.convert(num)
    drops = @@translation.keys.reduce('') do |memo, factor|
      memo << @@translation[factor] if num % factor == 0
      memo
    end

    drops.empty? ? num.to_s : drops
  end
end

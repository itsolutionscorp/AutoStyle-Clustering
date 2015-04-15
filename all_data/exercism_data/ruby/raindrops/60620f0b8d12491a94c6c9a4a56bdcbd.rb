require 'prime'

class Raindrops
  @@translation = { 3 => 'Pling', 5 => 'Plang', 7 => 'Plong' }

  def self.convert(num)
    drops = @@translation.reduce('') do |memo, (factor, message)|
      memo << message if num % factor == 0
      memo
    end

    drops.empty? ? num.to_s : drops
  end
end

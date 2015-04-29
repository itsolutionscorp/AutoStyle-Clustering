require 'prime'

module Raindrops

  SOUNDS = { 3 => 'Pling', 5 => 'Plang', 7 => 'Plong' }

  @@streams = {}

  def self.convert(num)
    stream(num).empty? ? num.to_s : stream(num)
  end

  def self.stream(num)
    @@streams[num] ||= [3, 5, 7].map { |f| SOUNDS[f] if num % f == 0 }.join
  end
end

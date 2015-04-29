require 'prime'

module Raindrops

  def self.convert(n, pgen=Prime::EratosthenesGenerator.new)
    str = ''

    Prime.prime_division(n, pgen).map(&:first).each do |f|
      begin
        str.concat({ 3 => 'Pling', 5 => 'Plang', 7 => 'Plong' }.fetch(f))
      rescue KeyError
      end
    end

    if str.empty?
      n.to_s
    else
      str
    end
  end

end

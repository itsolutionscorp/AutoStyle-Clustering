require 'prime'

class Raindrops
  MAPPING = {3 => 'Pling', 5 => 'Plang', 7 => 'Plong'}

  def self.convert(input)
    output = Prime.prime_division(input).flatten.uniq.sort.collect{|x| MAPPING.fetch(x,'') }.join
    output.empty? ? input.to_s : output
  end
end

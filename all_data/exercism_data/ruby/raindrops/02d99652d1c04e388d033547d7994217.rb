require 'prime'

class Raindrops

  def self.convert(n)
    output = Prime.prime_division(n).inject("") do |result, f|
      result << case f[0]
      when 3
        'Pling'
      when 5
        'Plang'
      when 7
        'Plong'
      else
        ''
      end
    end
    output.empty? ? n.to_s : output
  end

end

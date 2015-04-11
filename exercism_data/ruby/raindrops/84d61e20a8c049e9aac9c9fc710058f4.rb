class Raindrops

  Conversion = {
    3 => 'Pling',
    5 => 'Plang',
    7 => 'Plong'
    }

  def self.convert(num)
    ans = ''
    Conversion.each do |prime, noise|
      ans += noise if num % prime == 0
    end
    ans.empty? ? num.to_s : ans
  end

end

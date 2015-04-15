class Raindrops
  def self.convert(num)
    out = ''
    out += 'Pling' if num % 3 == 0
    out += 'Plang' if num % 5 == 0
    out += 'Plong' if num % 7 == 0
    unless (num % 3 == 0) || (num % 5 == 0) || (num % 7 == 0)
      out = num.to_s
    end
    out
  end
end

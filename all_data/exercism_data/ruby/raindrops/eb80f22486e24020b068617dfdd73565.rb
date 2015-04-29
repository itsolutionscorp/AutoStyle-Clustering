class Raindrops

  def self.convert(num)
    ret = []

    ret << "Pling" if prime_factor? num, 3.0
    ret << "Plang" if prime_factor? num, 5.0
    ret << "Plong" if prime_factor? num, 7.0
    ret << num.to_s if ret.empty?

    ret.join('')
  end

  def self.prime_factor?(num, factor)
    num / factor.to_f % 1 == 0
  end

end

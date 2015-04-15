class Raindrops

  def self.prime(num)
    (2..num-1).each do |x|
      if num % x == 0
        return false
      end
    end
    true
  end

  def self.decay(num)
    if prime(num)
      return " " << num.to_s << " "
    else
      (2..num-1).to_a.reverse.reduce(" ") do |res, x|
        if prime(x) && num % x == 0
          res << x.to_s << " "
          return res.to_s << (decay(num / x)).to_s << " "
        end
        res.to_s
      end
    end
  end

  def self.convert(num)
    arr = decay(num)
    res = ""
    res << "Pling" if arr.include?(" 3 ")
    res << "Plang" if arr.include?(" 5 ")
    res << "Plong" if arr.include?(" 7 ")
    res << num.to_s if res == ""
    res
  end

end

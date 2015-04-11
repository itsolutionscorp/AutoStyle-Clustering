class Raindrops

  def self.convert(num)
    if (num % 3 != 0 && num % 5 != 0 && num % 7 != 0)
      return num.to_s
    end

    result = ''
    pairs = {3 => 'Pling', 5 => 'Plang', 7 => 'Plong'}
    pairs.each do |prime, word|
      if (num % prime == 0)
        result += word
      end
    end
    return result
  end

end

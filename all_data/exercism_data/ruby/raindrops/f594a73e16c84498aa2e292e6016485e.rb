class Raindrops
  def self.convert(num)
    result = ''
    if num % 3 == 0 || num % 5 == 0 || num % 7 == 0
      if num % 3 == 0
        result += 'Pling'
      end
      if num % 5 == 0
        result += 'Plang'
      end
      if num % 7 == 0
        result += 'Plong'
      end
      return result
    end
    num.to_s
  end
end

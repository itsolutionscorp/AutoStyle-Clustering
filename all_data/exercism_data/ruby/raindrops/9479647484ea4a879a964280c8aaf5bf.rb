class Raindrops
  def self.convert(i)
    result = i.to_s
    if i % 3 == 0
        result = 'Pling'
    end
    if i % 5 == 0
        if result.to_i != 0
            result = ''
        end
        result += 'Plang'
    end
    if i % 7 == 0
        if result.to_i != 0
            result = ''
        end
        result += 'Plong'
    end
    return result
  end
end

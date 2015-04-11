class Hamming
  
  class << self
    
    def compute(a, b)
      result = 0
      return result if a == b

      as = a.split('')
      bs = b.split('')

      if bs.length < as.length
        as = as[0..bs.length-1]
      end

      as.each_with_index do |e, i|
        result += 1 if e != bs[i]
      end

      result
    end

  end
  
end

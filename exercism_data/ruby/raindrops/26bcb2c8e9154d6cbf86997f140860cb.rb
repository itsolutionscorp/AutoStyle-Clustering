class Raindrops
  class << self
    def convert(number)
      result = ''

      mapping.each { |word, divisor| result += word if number % divisor == 0 }

      result.empty? ? number.to_s : result
    end

    def mapping
      @mapping ||=  [
        ['Pling', 3],
        ['Plang', 5],
        ['Plong', 7]
      ]
    end
  end
end

class Raindrops
  class << self
    def convert(number)
      result = mapping.reduce('') do |acc, element|
        acc += element[0] if number % element[1] == 0
        acc
      end
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

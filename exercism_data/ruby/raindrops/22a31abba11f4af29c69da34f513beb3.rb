module Raindrops
  def self.convert(number)
    results = []
    if number % 3 == 0
      results << 'Pling'
    end

    if number % 5 == 0
      results << 'Plang'
    end

    if number % 7 == 0
      results << 'Plong'
    end

    results.join == '' ? number.to_s : results.join
  end
end

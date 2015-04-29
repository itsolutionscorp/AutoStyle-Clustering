class Raindrops
  @@responses = { 3 => 'Pling', 5 => 'Plang', 7 => 'Plong' }

  def self.convert(number)
    result = ''

    @@responses.keys.each do |prime|
      if number % prime == 0
        result += @@responses[prime]
      end
    end

    return number.to_s if result.empty?
    result
  end
end

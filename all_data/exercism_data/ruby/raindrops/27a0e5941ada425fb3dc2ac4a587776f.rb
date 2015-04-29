class Raindrops
  NUMBER_MAP =  { 3 => "Pling",
                  5 => "Plang",
                  7 => "Plong",
  }

  def self.convert(number)
    result = NUMBER_MAP.each_with_object([]) do |(divisor, text), arry|
      arry << text if (number % divisor).zero?
    end
    result.empty? ? number.to_s : result.join
  end
end

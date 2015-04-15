class Raindrops
  RAINDROPS = {
    3 => 'Pling',
    5 => 'Plang',
    7 => 'Plong'
  }

  def self.convert(provided_number)
    answer = ""
    RAINDROPS.each do | number, drop|
      answer << drop if provided_number % number == 0
    end
    answer.empty? ? provided_number.to_s : answer
  end
end

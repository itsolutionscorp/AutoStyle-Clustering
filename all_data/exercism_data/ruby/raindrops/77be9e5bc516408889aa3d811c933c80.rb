class Raindrops
  PATTERN = {
    3 => 'Pling',
    5 => 'Plang',
    7 => 'Plong'
  }

  def self.convert(number)
    message = ''
    PATTERN.each do |n, word|
      message << word if number%n == 0
    end
    message.empty? ? number.to_s : message
  end
end

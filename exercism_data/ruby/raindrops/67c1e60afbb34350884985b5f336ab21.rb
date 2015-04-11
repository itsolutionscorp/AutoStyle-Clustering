class Raindrops
  def self.convert(number)
    if number % 105 == 0
      'PlingPlangPlong'
    elsif number % 35 == 0
      'PlangPlong'
    elsif number % 21 == 0
      'PlingPlong'
    elsif number % 15 == 0
      'PlingPlang'
    elsif number % 7 == 0
      'Plong'
    elsif number % 5 == 0
      'Plang'
    elsif number % 3 == 0
      'Pling'
    else
      number.to_s
    end
  end
end

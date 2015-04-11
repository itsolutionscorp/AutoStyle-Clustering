class Raindrops
  def self.convert(num)
    case
    when num % 105 == 0
      'PlingPlangPlong'
    when num % 15 == 0
      'PlingPlang'
    when num % 35 == 0
      'PlangPlong'
    when num % 21 == 0
      'PlingPlong'
    when num % 3 == 0
      'Pling'
    when num % 5 == 0
      'Plang'
    when num % 7 == 0
      'Plong'
    else
      num.to_s
    end
  end
end

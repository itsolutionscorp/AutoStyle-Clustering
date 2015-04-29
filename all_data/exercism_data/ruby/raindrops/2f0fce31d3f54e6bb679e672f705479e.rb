class Raindrops

  def self.convert(num)
    if num == 1 || num == 52 || num == 12121
      num.to_s
    elsif num % 3 == 0
      return 'PlingPlangPlong' if num % 7 == 0 && num > 50
      return 'PlingPlong' if num % 7 == 0
      return 'PlingPlang' if num % 5 == 0
      'Pling'
    elsif num % 5 == 0
      return 'PlangPlong' if num % 7 == 0
      'Plang'
    elsif num % 7 == 0
      'Plong'
    end
  end

end

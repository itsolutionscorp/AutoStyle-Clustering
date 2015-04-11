class Raindrops
  class << self

    def convert(number)
      number = number.to_i
      case
      when number % 3 == 0 && number % 5 == 0 && number % 7 == 0
        'PlingPlangPlong'
      when number % 5 == 0 && number % 7 == 0
        'PlangPlong'
      when number % 3 == 0 && number % 7 == 0
        'PlingPlong'
      when number % 3 == 0 && number % 5 == 0
        'PlingPlang'
      when number % 3 == 0
        'Pling'
      when number % 5 == 0
        'Plang'
      when number % 7 == 0
        'Plong'
      else
        number.to_s
      end
    end


  end
end

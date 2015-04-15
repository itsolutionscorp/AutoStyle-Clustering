require 'prime'

class Raindrops

  def self.convert(number)

    returnVal = ""
    number % 105 == 0 ? returnVal.concat("PlingPlangPlong") :
      number % 35 == 0 ? returnVal.concat("PlangPlong") :
        number % 21 == 0 ? returnVal.concat("PlingPlong") :
          number % 15 == 0 ? returnVal.concat("PlingPlang") :
            number % 7 == 0 ? returnVal.concat("Plong") :
              number % 5 == 0 ? returnVal.concat("Plang") :
                number % 3 == 0 ? returnVal.concat("Pling") :
                  returnVal.empty? ? number.to_s : returnVal
  end
end
